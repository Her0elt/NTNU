cmake_minimum_required(VERSION 3.0.0)
project(Task3 VERSION 0.1.0)

include(CTest)
enable_testing()

add_executable(Task12 Task12/Circle.cpp Task12/Circle.hpp)
add_executable(Task3  Task3/Commodity.cpp  Task3/Commodity.hpp)
add_executable(Task4  Task4/main.cpp)


set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
