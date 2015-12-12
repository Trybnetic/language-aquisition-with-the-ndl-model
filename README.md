# Language Aquisition with the NDL model

## Abstract

In this study we modeled a simple agent and implemented a learning algorithm based on the naive discriminative learning (NDL) model. At the beginning of each trial the agent receives a cue where a target can be found. At the beginning the agent has no information about the meaning of the cues. The goal of our study was to show how the associations for task relevant words gets learned whereas associations for task-irrelevant words do not.

## Run the simulation

1. Clone this repository  
	`git clone https://github.com/Trybnetic/language-aquisition-with-the-ndl-model.git`
2. Make the run file executable  
	`chmod +x run`
3. Run the simulation  
	`./run`
4. Now you have a .txt file with the learned Rescorla-Wagner-Values

## Run the simulation customized

1. Clone this repository  
	`git clone https://github.com/Trybnetic/language-aquisition-with-the-ndl-model.git`
2. Create the binary files  
	`make`
3. Change the directory to bin/  
	`cd bin/`
4. Run the simulation with your customized parameters, e.g.  
	`java Main -a 0.1 -b1 0.1 -b2 0.1 -l 1 -n 200000`  
5. Clean up  
	`cd ..`  
	`make clean`
4. Now you have a .txt file with the learned Rescorla-Wagner-Values

## License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a>

This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Attribution-ShareAlike 4.0 International License</a>.
