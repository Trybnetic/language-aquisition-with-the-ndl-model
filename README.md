# Language Aquisition with the NDL model

[![License](https://img.shields.io/github/license/Trybnetic/language-aquisition-with-the-ndl-model.svg)](https://github.com/Trybnetic/language-aquisition-with-the-ndl-model/blob/master/LICENSE.txt)  

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
This work is [licensed under the MIT license](https://github.com/Trybnetic/language-aquisition-with-the-ndl-model/blob/master/LICENSE.txt). Check the license on whether and how you are allowed to use, modify and distribute this theme.
