FROM gcc:9.3.0
RUN mkdir /cpp
WORKDIR /cpp
COPY main.cpp /cpp/
RUN g++ -o main main.cpp
CMD ["./main"]