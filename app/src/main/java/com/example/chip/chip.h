//
// Created by Jim Olson on 5/31/2019.
//

#ifndef CHIP_CHIP_H
#define CHIP_CHIP_H

#pragma once
#include "core.h"
#include <vector>
#include <ctime>
#include <sapi.h>
#include <jni.h>


JNIEXPORT jstring JNICALL
 Java_com_foo_sample_FooActivity_getMsgFromJni(JNIEnv *env, jobject obj) {
 return (*env)->NewStringUTF(env, "Hello World NDK");
 }

class CHIP
{

public:
	CHIP();
	~CHIP();

	void toogleOnOff();

	void getTimeDate();

	std::string greet();

	//Input Handlers

	std::string getInput();

	void cleanInput(std::string input);

	void processInput(std::string input);

	void readInput(std::string input);

	//void streamOutput(std::string input);

	// Actions
	void decide(std::string input);

	void mimic();

	void question();

	void commitToSTM();

	void answer();

	void waits();

	//std::wstring s2ws(const std::string& s);

	//int speak();

	void incrementTime();

	//actionQueue
	std::string minput;

private:

	bool isOn;

	chiptime time;

	bool listening;

	bool isHappy;

	int mood;

	std::string personOfAddress;

	std::vector<std::string> stm;

	std::vector<std::string> ltm;

	//std::thread t1;
	//std::thread t2;

};





#endif //CHIP_CHIP_H
