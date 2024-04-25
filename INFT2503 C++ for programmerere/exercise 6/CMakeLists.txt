cmake_minimum_required(VERSION 3.0.0)
project(exercise6 VERSION 0.1.0)



set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -pthread -Wall -Wextra")


add_executable(HTTPServer HTTPServer.cpp)
add_executable(Chess Chess.cpp)


find_package(Boost 1.54.0 COMPONENTS system REQUIRED)
include_directories(${Boost_INCLUDE_DIR})
