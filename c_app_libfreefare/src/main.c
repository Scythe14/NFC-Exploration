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
  nfc_context           *context;
  nfc_device            *device;
  FreefareTag           *tags = NULL;
  int                   i;

  if (init_nfc_context(&context, &device) == FAIL)
    return (FAIL);
  tags = freefare_get_tags(device);
  if (!tags)
    {
      printf("No tags found\n");
      return (0);
    }
  
  //  detect_tags(tags);

  struct mifare_desfire_version_info info;

  mifare_desfire_connect(tags[0]);
  mifare_desfire_get_version(tags[0], &info);
  printf("%c - %c\n", info.production_week, info.production_year);
  return (0);
}
