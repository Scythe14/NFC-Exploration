#ifndef TOOLS_H_
# define TOOLS_H_

# define FAIL		-1
# define MAX_TARGET     8

// See enum freefare_tag_type and change following array accorging to it

typedef unsigned char byte_t;

const unsigned char keys[10][6] = {
  {0xd3, 0xf7, 0xd3, 0xf7, 0xd3, 0xf7},
  {0xa0, 0xa1, 0xa2, 0xa3, 0xa4, 0xa5},
  {0xb0, 0xb1, 0xb2, 0xb3, 0xb4, 0xb5},
  {0xff, 0xff, 0xff, 0xff, 0xff, 0xff},
  {0x4d, 0x3a, 0x99, 0xc3, 0x51, 0xdd},
  {0x1a, 0x98, 0x2c, 0x7e, 0x45, 0x9a},
  {0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff},
  {0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
  {0x46, 0xeb, 0x37, 0x86, 0x49, 0xfc},
  {0x8f, 0x43, 0x80, 0x0f, 0x8c, 0x7b},
};

# endif /* TOOLS_H_ */