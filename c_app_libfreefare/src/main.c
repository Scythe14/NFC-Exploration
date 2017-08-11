#include <stdlib.h>
#include <string.h>
#include <nfc/nfc.h>
#include <freefare.h>
#include <tools.h>

int sterr(char *s)
{
  fprintf(stderr, "Error: %s\n", s);
  return (FAIL);
}

void                    detect_tags(FreefareTag *tags)
{
  int                   i;
  
  i = -1;
  while (tags[++i])
    printf("Card %d : %s\n", i, freefare_get_tag_friendly_name(tags[i]));
}

int                     init_nfc_context(nfc_context **context, nfc_device **device)
{
  nfc_init(context);
  if (context == NULL)
    return (sterr("Unable to init libnfc"));
  if ((*device = nfc_open(*context, NULL)) == NULL)
    return (sterr("Unable to open NFC device"));
  printf("NFC Reader: %s opened\n", nfc_device_get_name(*device));
  return (0);
}

int                     main(int ac, char **av)
{
  struct mifare_desfire_version_info info;
  nfc_context           *context;
  nfc_device            *device;
  FreefareTag           *tags;
  int                   res;

  res = FAIL;
  tags = NULL;
  if (init_nfc_context(&context, &device) == FAIL)
    return (FAIL);
  tags = freefare_get_tags(device);
  if (!tags) {
    nfc_close(device);
    return sterr("No tags found\n");
  }
  //  detect_tags(tags);
  
  if (MIFARE_DESFIRE != freefare_get_tag_type(tags[0]))  
    return sterr("Card is not a Desfire recognized card");
  if (mifare_desfire_connect(tags[0]) < 0)
    return sterr("Can't connect to Mifare DESFire target.");
  
  mifare_desfire_get_version(tags[0], &info);

  printf("===> Version information for tag %s:\n", freefare_get_tag_uid(tags[0]));
  printf("UID:                      0x%02x%02x%02x%02x%02x%02x%02x\n", info.uid[0], info.uid[1], info.uid[2], info.uid[3], info.uid[4], info.uid[5], info.uid[6]);
  printf("Batch number:             0x%02x%02x%02x%02x%02x\n", info.batch_number[0], info.batch_number[1], info.batch_number[2], info.batch_number[3], info.batch_number[4]);
  printf("Production date:          week %x, 20%02x\n", info.production_week, info.production_year);
  printf("Hardware Information:\n");
  printf("    Vendor ID:            0x%02x\n", info.hardware.vendor_id);
  printf("    Type:                 0x%02x\n", info.hardware.type);
  printf("    Subtype:              0x%02x\n", info.hardware.subtype);
  printf("    Version:              %d.%d\n", info.hardware.version_major, info.hardware.version_minor);
  printf("    Storage size:         0x%02x (%s%d bytes)\n", info.hardware.storage_size, (info.hardware.storage_size & 1) ? ">" : "=", 1 << (info.hardware.storage_size >> 1));
  printf("    Protocol:             0x%02x\n", info.hardware.protocol);
  printf("Software Information:\n");
  printf("    Vendor ID:            0x%02x\n", info.software.vendor_id);
  printf("    Type:                 0x%02x\n", info.software.type);
  printf("    Subtype:              0x%02x\n", info.software.subtype);
  printf("    Version:              %d.%d\n", info.software.version_major, info.software.version_minor);
  printf("    Storage size:         0x%02x (%s%d bytes)\n", info.software.storage_size, (info.software.storage_size & 1) ? ">" : "=", 1 << (info.software.storage_size >> 1));
  printf("    Protocol:             0x%02x\n", info.software.protocol);
  
  return (0);
}
