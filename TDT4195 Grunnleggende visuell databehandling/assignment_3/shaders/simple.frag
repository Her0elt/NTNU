#version 430 core

out vec4 color;

in vec4 vertexColor;
in vec3 vertexNormals;

vec3 lightDirection = normalize(vec3(0.8, -0.5, 0.6));

void main()
{
    vec4 norm4 = vec4(vertexNormals, 1.0);
    color = vec4(vertexColor.xyz * max(0, dot(vertexNormals, -lightDirection)), vertexColor.w);
}
