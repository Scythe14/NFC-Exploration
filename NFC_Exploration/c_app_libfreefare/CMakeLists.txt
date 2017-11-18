cmake_minimum_required(VERSION 3.5)
project(nfc-profiling)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS}")


include_directories(include)
include_directories(/usr/local/lib/libfreefare/include)
link_directories(/usr/local/lib/libfreefare/lib)

file(GLOB SOURCE_FILES
	  "src/*.c"
	  "include/*.h")

add_executable(nfc-profiling ${SOURCE_FILES})
target_link_libraries(nfc-profiling -lnfc -lfreefare)
