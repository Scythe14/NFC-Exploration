cmake_minimum_required(VERSION 3.5)
project(nfc-profiling)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS}")


include_directories(include)

file(GLOB SOURCE_FILES
	  "src/*.c"
	  "include/*.h")

add_executable(nfc-profiling ${SOURCE_FILES})
target_link_libraries(nfc-profiling -lnfc)
