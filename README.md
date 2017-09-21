# README #

This is the _Random Music Generator_ (RMG), a project written in Java to create random melodies played on a piano. I started writing it for fun, but eventually used it as an in-class demonstration of algorithmic music in 2013 (which was based on concepts introduced in _Notes from the Metalevel_ by Heinrich K. Taube) as I was interested in how to capture different aspects of music programmatically.

It is able to create random melodies for which you numerically set the number of notes to contain (e.g. 20). Optionally, it will also play chords in a not-so-random fashion. It outputs the notes and note values on the shell and saves it as a .txt file every time you use it alongside a serialized version which you can play back later using the MelGetter class. Note that the musical notation and many of the directories and/or documents are in German; I plan on changing it to English at some point, though.

The whole premise of the project is heavily based on Goldbach's conjecture (https://en.wikipedia.org/wiki/Goldbach%27s_conjecture) and it basically works by assigning each note an even integer, and each note value a prime number. The idea is then to randomly pick two primes, add them to get the note to be played, and use one of the primes as its note value. RandomFibo is a manifestation of that approach. There is additionally RandomFibo_2 that picks the note value not from the prime numbers that make up a note, but instead randomly as well.

In order distribute the notes a little bit better (i.e. so that it's a little bit nicer to listen to), I additionally did some tweaking and optimized the distributions towards a middle-note-centered range (since the larger an even number is, the more possible prime number tuples that add up to it there are, the numerically 'lower' notes were statistically less likely to occur than the numerically 'higher' notes; this is why I optimized it).

### Structure ###

I didn't change any significant parts yet, so it's quite unstructured at the moment. You'll find MIDI files for the piano in /MIDI/Klavier, some sample output .txt and serialized files in sample_output/, and the source code in /src. There are furthermore some fragments of earlier experiments and documents regarding findings of and statistics on the project that are dispersed in /Algo and /Dokumente.

* Algo
* Dokumente
* MIDI
* sample_output
* src

### How to use it ###

After compiling all the classes in /src, you can invoke the main program, RandomFibo_2 by simply typing _java RandomFibo_2 [someNumber]_
(The difference between RandomFibo and RandomFibo_2 is that the latter also generates the note values for each note to be played randomly, whereas the former derives it from the composition of the note itself.)

From the help menu of RandomFibo_2 (_java RandomFibo_2 -help_):

"You can use it in two ways:

* Generate a melody without chords by executing the program with only one argument. This argument should be the number of notes you wish the melody to have. An example execution looks like this: _java RandomFibo 20_

* Generate a melody with chords by providing two arguments. The first argument, "+Chords", is to indicate that you wish chords to be included. The second is again the number of total notes (including chords) you wish the melody to have. Be advised that you cannot decide where chords will be played. But maybe you will be able to spot the pattern by which they are generated? An example execution thus looks like this: _java RandomFibo +Chords 20_"

### Who do I talk to? ###

* Mumon
