#include <nfc/nfc.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mifare.h"
#include "tools.h"

void                    print_nfc_target(const nfc_target *device, bool verbose)
{
  char                  *s;

  str_nfc_target(&s, device, verbose);
  printf("%s", s);
  nfc_free(s);
}

void                    print_hex(const uint8_t *pbtData, const size_t szBytes)
{
  size_t                szPos;

  for (szPos = 0; szPos < szBytes; szPos++)
    printf("%02x  ", pbtData[szPos]);
  printf("\n");
}

int                     sterr(char *s)
{
  fprintf(stderr, "Error: %s\n", s);
  return (FAIL);
}

int                     init(nfc_context **context, nfc_device **device)
{
  const char            *libnfc_version;

  nfc_init(context);
  if (!context)
    return (sterr("Unable to init LibNFC"));
  if ((*device = nfc_open(*context, NULL)) == NULL)
    return (sterr("Unable to open NFC device"));
  libnfc_version = nfc_version();
  printf("NFC Reader: %s opened\n", nfc_device_get_name(*device));
  printf("libnfc version: %s\n", libnfc_version);
  return (0);
}

int                     main(int ac, const char **av)
{
  nfc_context           *context;
  nfc_device            *device;
  nfc_target            *ant;
  nfc_modulation        nm;
  size_t                i;
  bool verbose = false;
  int res = 0;
  int mask = 0x1ff;

  (void)ac;
  (void)av;
  init(&context, &device);
  if (nfc_initiator_init(device) < 0) {
    nfc_perror(device, "nfc_initiator_init");
    nfc_exit(context);
    return (FAIL);
  }
    
  if (mask & 0x1) {
    nm.nmt = NMT_ISO14443A;
    nm.nbr = NBR_106;
    // List ISO14443A targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443A passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x02) {
    nm.nmt = NMT_FELICA;
    nm.nbr = NBR_212;
    // List Felica tags
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d Felica (212 kbps) passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x04) {
    nm.nmt = NMT_FELICA;
    nm.nbr = NBR_424;
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d Felica (424 kbps) passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x08) {
    nm.nmt = NMT_ISO14443B;
    nm.nbr = NBR_106;
    // List ISO14443B targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443B passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x10) {
    nm.nmt = NMT_ISO14443BI;
    nm.nbr = NBR_106;
    // List ISO14443B' targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443B' passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x20) {
    nm.nmt = NMT_ISO14443B2SR;
    nm.nbr = NBR_106;
    // List ISO14443B-2 ST SRx family targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443B-2 ST SRx passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x40) {
    nm.nmt = NMT_ISO14443B2CT;
    nm.nbr = NBR_106;
    // List ISO14443B-2 ASK CTx family targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443B-2 ASK CTx passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }
  
  if (mask & 0x80) {
    nm.nmt = NMT_JEWEL;
    nm.nbr = NBR_106;
    // List Jewel targets
    if ((res = nfc_initiator_list_passive_targets(device, nm, ant, MAX_TARGET)) >= 0) {
      int n;
      if (verbose || (res > 0)) {
        printf("%d ISO14443A-3 Jewel passive target(s) found%s\n", res, (res == 0) ? ".\n" : ":");
      }
      for (n = 0; n < res; n++) {
        print_nfc_target(ant, verbose);
        printf("\n");
      }
    }
  }

  nfc_configure(device, NP_ACTIVATE_FIELD,false);
  nfc_configure(device, NP_INFINITE_SELECT,false);
  nfc_configure(device, NP_HANDLE_CRC,true);
  nfc_configure(device, NP_HANDLE_PARITY,true);

  int sector;
  for (sector = 1; sector < 9; sector++) {
    printf("Getting sector %d\n",sector);
    int authBlock = (sector*4)-1;
    int startBlock = authBlock-3;
    bool auth = authenticate(device, mp, target, authBlock, 0, false);
    if (auth)
      readblocks(device, mp, startBlock, authBlock);
  }
  
  nfc_close(device); 
  nfc_exit(context);
  exit(EXIT_SUCCESS);
}
