{\rtf1\ansi\ansicpg1252\cocoartf2580
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww12340\viewh13960\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 **MixThis**\
\
\
This app tells you the ingredients to make a cocktail if it finds an exact match (capitalization excluded) for the cocktail name you search for.\
\
**Description and Features**\
\
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0
\cf0 Pictures of the application are located in the MixThis/pictures folder.\
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0
\cf0 \
MixThis is a simple app that utilizes a search api form www.thecocktaildb.com; it was built while I learned to use public API on Android applications.\
\
Once the user enters input, the name of a drink, the application will parse through the returned JSON of matches; it will only look at the first returned drink and determine if that drink is an exact match. It will then parse through and GET all the ingredients as well as an image of the cocktail. The separate thread these processes are ran on will also dis play a circular progress bar until the thread\'92s tasks are complete.\
\
\
**Installing and running program**	\
\
	\'95	The .APK file can be found under MixThis/app/release\
	\'95	The .APK file can run on most android devices; tested on Pixel 3.\
	\'95	To run via AndroidStudito, navigate to \
		File -> new -> project from version control. Enter URL: https://github.com/zachasyl/SprintFit.git.\
\
\
**Authors**\
\
\'95Zachary Sylvane}