#version 430 core

out vec4 color;

in vec4 vertexColor;

void main()
{
    color = vertexColor;
}
