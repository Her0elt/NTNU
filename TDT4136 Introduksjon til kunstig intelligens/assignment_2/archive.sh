if test -f source.zip; then
	rm -v source.zip
fi
zip -r source.zip \
	Cargo.lock \
	Cargo.toml \
	src \
    astar/ \
