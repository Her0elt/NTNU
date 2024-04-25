cmake_minimum_required(VERSION 3.0.0)
project(exercise7 VERSION 0.1.0)


add_executable(fraction main.cpp fraction.cpp set.cpp)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
