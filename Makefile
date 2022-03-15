clean:
	rm -f journal4*

run:
	lein ring server-headless

clean-run: clean run
