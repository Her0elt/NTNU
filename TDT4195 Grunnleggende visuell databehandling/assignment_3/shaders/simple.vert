#version 430 core

in layout (location = 0) vec3 position;

in layout (location = 1) vec4 color;

in layout (location = 3) vec3 normals;

//uniform layout(location = 2) float sin;

uniform layout(location = 2) mat4x4 matrixVariable;

uniform layout(location = 4) mat4x4 modelMat;


out vec4 vertexColor;
out vec3 vertexNormals;

void main()
{
    //mat4x4 matrixVariable = mat4(1);
    //matrixVariable[0][0] = sin;
    gl_Position = matrixVariable * vec4(position, 1.0f);
    vertexColor = color;
    vertexNormals = normalize(mat3(modelMat) * normals);
}
