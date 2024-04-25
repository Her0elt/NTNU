#!/bin/sh
if test -f source.zip; then
	rm -v source.zip
fi
zip -r source.zip \
	Cargo.lock \
	Cargo.toml \
	src \
    report_final.pdf \
	shaders \
	resources/* \
	-x"resources/helicopter.obj" \
	-x"resources/lunarsurface.obj" \
	-x"resources/.gitkeep"
