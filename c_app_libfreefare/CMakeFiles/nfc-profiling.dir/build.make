# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/dany/NFC-Exploration/c_app

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/dany/NFC-Exploration/c_app

# Include any dependencies generated for this target.
include CMakeFiles/nfc-profiling.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/nfc-profiling.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/nfc-profiling.dir/flags.make

CMakeFiles/nfc-profiling.dir/src/main.c.o: CMakeFiles/nfc-profiling.dir/flags.make
CMakeFiles/nfc-profiling.dir/src/main.c.o: src/main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dany/NFC-Exploration/c_app/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/nfc-profiling.dir/src/main.c.o"
	/usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/nfc-profiling.dir/src/main.c.o   -c /home/dany/NFC-Exploration/c_app/src/main.c

CMakeFiles/nfc-profiling.dir/src/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/nfc-profiling.dir/src/main.c.i"
	/usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/dany/NFC-Exploration/c_app/src/main.c > CMakeFiles/nfc-profiling.dir/src/main.c.i

CMakeFiles/nfc-profiling.dir/src/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/nfc-profiling.dir/src/main.c.s"
	/usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/dany/NFC-Exploration/c_app/src/main.c -o CMakeFiles/nfc-profiling.dir/src/main.c.s

CMakeFiles/nfc-profiling.dir/src/main.c.o.requires:

.PHONY : CMakeFiles/nfc-profiling.dir/src/main.c.o.requires

CMakeFiles/nfc-profiling.dir/src/main.c.o.provides: CMakeFiles/nfc-profiling.dir/src/main.c.o.requires
	$(MAKE) -f CMakeFiles/nfc-profiling.dir/build.make CMakeFiles/nfc-profiling.dir/src/main.c.o.provides.build
.PHONY : CMakeFiles/nfc-profiling.dir/src/main.c.o.provides

CMakeFiles/nfc-profiling.dir/src/main.c.o.provides.build: CMakeFiles/nfc-profiling.dir/src/main.c.o


# Object files for target nfc-profiling
nfc__profiling_OBJECTS = \
"CMakeFiles/nfc-profiling.dir/src/main.c.o"

# External object files for target nfc-profiling
nfc__profiling_EXTERNAL_OBJECTS =

nfc-profiling: CMakeFiles/nfc-profiling.dir/src/main.c.o
nfc-profiling: CMakeFiles/nfc-profiling.dir/build.make
nfc-profiling: CMakeFiles/nfc-profiling.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/dany/NFC-Exploration/c_app/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable nfc-profiling"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/nfc-profiling.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/nfc-profiling.dir/build: nfc-profiling

.PHONY : CMakeFiles/nfc-profiling.dir/build

CMakeFiles/nfc-profiling.dir/requires: CMakeFiles/nfc-profiling.dir/src/main.c.o.requires

.PHONY : CMakeFiles/nfc-profiling.dir/requires

CMakeFiles/nfc-profiling.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/nfc-profiling.dir/cmake_clean.cmake
.PHONY : CMakeFiles/nfc-profiling.dir/clean

CMakeFiles/nfc-profiling.dir/depend:
	cd /home/dany/NFC-Exploration/c_app && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/dany/NFC-Exploration/c_app /home/dany/NFC-Exploration/c_app /home/dany/NFC-Exploration/c_app /home/dany/NFC-Exploration/c_app /home/dany/NFC-Exploration/c_app/CMakeFiles/nfc-profiling.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/nfc-profiling.dir/depend
