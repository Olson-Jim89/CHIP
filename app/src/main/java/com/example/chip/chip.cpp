//
// Created by Jim Olson on 5/31/2019.
//

#include "chip.h"
#include <locale>
#include <thread>
#include <iostream>
#include <iterator>
#include <sstream>

std::wstring s2ws(const std::string & s);

void streamOutput(std::string input);

int createSound(std::string input);
std::vector<std::string> split(std::string input);


CHIP::CHIP()
{
	isOn = true;
	isHappy = true;
	mood = 50;
	personOfAddress = "";
	decide();
	greet();
	//requestInput();
}

CHIP::~CHIP()
{
}

void CHIP::toogleOnOff()
{
	if (isOn == false) {
		isOn = true;
	}
	else if (isOn == true) {
		isOn = false;
	}
}

void CHIP::getTimeDate()
{
	time.day = 1;
}

std::string CHIP::greet() {
	std::string greeting = "I am CHIP.";
	std::string inqueryName = "Who are you?";

	speak(greeting);
	speak(inqueryName);

	return getInput();

}

std::string CHIP::getInput()
{
	std::string input;

	while (input != "<off>")
	{
		std::cout << "\nInput: ";
		std::getline(std::cin,input);
		processInput(input);
	}
}

void CHIP::cleanInput(std::string input)
{
	std::locale loc;
	for (std::string::size_type i = 0; i < input.length(); ++i)
	{
		std::tolower(input[i], loc);
	}
}

void CHIP::processInput(std::string input)
{
	cleanInput(input);
	stm.push_back(input);

	//mimic(input);
	split(input);
}

void CHIP::readInput(std::string input)
{
	std::vector<std::string> words = split(input);

	std::string firstWord = words[0];

	if (firstWord == "what" || firstWord == "when" || firstWord == "where" || firstWord == "why") {
		speak("That is a question.");
	}
	else if (firstWord == "command") {
		speak("That is a command");
	}
	else
	{

		speak("That is a statment");
	}


}

void CHIP::decide(std::string input)
{
	if (personOfAddress == "") {

		personOfAddress = greet();
		// if unknown name create person
		// generate name if user dose not answer
	}

	int task = 0;

	switch(task)
	{
		case 0:
			getInput();
			break;
		case 1:
			mimic();
			break;
		case 2:
			getTime();
			break;
		default:
			break;
	}

}

void CHIP::mimic()
{
	std::string input;

	while (input != "<off>")
	{
		std::cout << "\nInput: ";
		std::getline(std::cin, input);
		speak(input);
	}
}

void CHIP::commitToSTM()
{
}

std::vector<std::string> split(std::string input) {
	std::istringstream iss(input);
	std::vector<std::string> words((std::istream_iterator<std::string>(iss)),
									std::istream_iterator<std::string>());

	for (int i = 0; i < words.size(); i++) {
		std::cout << words[i] << std::endl;
	}
	return words;
}

std::wstring s2ws(const std::string & s)
{
	int len;
	int slength = (int)s.length() + 1;
	len = MultiByteToWideChar(CP_ACP, 0, s.c_str(), slength, 0, 0);
	wchar_t* buf = new wchar_t[len];
	MultiByteToWideChar(CP_ACP, 0, s.c_str(), slength, buf, len);
	std::wstring r(buf);
	delete[] buf;
	return r;
}

void streamOutput(std::string input)
{
	std::cout << "CHIP: ";
	int i = 0;
	while (input[i] != '\0') {
		std::cout << input[i];
		Sleep(50);
		i++;
	}
	std::cout << std::endl;
}

int createSound(std::string input)
{
	ISpVoice * pVoice = NULL;

	if (FAILED(::CoInitialize(NULL)))
		return EXIT_FAILURE;

	HRESULT hr = CoCreateInstance(CLSID_SpVoice, NULL, CLSCTX_ALL, IID_ISpVoice, (void **)&pVoice);
	if (SUCCEEDED(hr))
	{

		std::string speakstring = input;

		std::wstring stemp = s2ws(speakstring);

		//std::cout << "testspeak:" << stm.back();

		hr = pVoice->Speak((LPCWSTR)stemp.c_str(), NULL, NULL);


		pVoice->Release();
		pVoice = NULL;
		return 0;
	}
	::CoUninitialize();
	return 0;
}

void speak(std::string input) {
	std::thread t1(streamOutput, input);
	std::thread t2(createSound, input);

	t1.join();
	t2.join();
}





