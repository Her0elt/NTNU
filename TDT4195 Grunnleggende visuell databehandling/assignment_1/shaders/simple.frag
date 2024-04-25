#version 430 core
in vec4 gl_FragCoord;
out vec4 color;

void main()
{

    float trigColor = (int(gl_FragCoord.x  / 10) % 2) == (int(gl_FragCoord.y / 10) % 2) ? 1.0f : 0.0f;
    color = vec4(trigColor, trigColor, trigColor, 1.0f);
}
