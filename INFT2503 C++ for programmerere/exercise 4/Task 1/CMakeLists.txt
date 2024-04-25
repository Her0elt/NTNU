cmake_minimum_required(VERSION 3.1)

project("Task 2")

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -Wall -Wextra -Wno-unused-parameter")

add_executable(main main.cpp)
