# XILIFFY
Xiliffy-compile is a compiler for .xiliffy files written in java.\
It can be used to simplify the creation of .xlf files for typo3 or other project using xliff files as translation source

# Usage

## Usage with the commandline

From the commandline you can simply type `java -jar <path>XILIFFY.jar <.xiliffy file path> [output folder]`\
if no output folder is specified it will use the folder of the .xiliffy file by default

## Setting up a filewatcher for IDEA IDEs (Optional)

For a simple setup, copy the xiliffywatcher.xml and import it under **Preferences | Tools | File Watchers**\
After this it will show as "unknown scope". To resolve this issue, select the filewatcher and click the pencil icon on the right\
There you will be prompted with a new dialog. At the top will be a field for scope.\
Next to this field is a button with 3 dots, click this. Then create a new scope by pressing the +.\
You can select local or shared depending on which you feel more suited for your needs\
Now you can give it a name and it will open a new window.\
Here you will have to type `file:*.xiliffy` as pattern\
![Scope view](https://i.imgur.com/KtD7FFe.png) \
Now click apply and exit the views until you get back to the edit file watcher ui

It should now look something like this:
![Watcher view](https://i.imgur.com/XS7NjMW.png) \
Here you will now have to select the jar as Program. Simply click the folder icon then find and select the jar \

Now you can simply create a folder in your language folder which will contain the .xiliffy and put the files inside.\

## Filenames

Xiliffy will slighly modify the filenames.\
The prefix of each file will differ based on the target language.\
The source language will not receive a prefix\

|Name|Output|
|----|------|
|locallang.xiliffy|locallang.xlf|
|db.xiliffy|locallang_dx.xlf|
|\<other>.xiliffy|locallang_csh_\<other>.xlf|

So for typo3 you would have the files
- locallang.xiliffy
- db.xiliffy
- tx\_\<product>\_domain_model\_\<model>.xiliffy

## Filestructure

Xiliffy files start with a head as metadata\
This contains

|Key|Description|
|---|-----------|
|source|The sourcelanguage of the project|
|product|The product identifier|

After this begins the actual content of the file\
The structure of the file is similar to yaml or scss, using whitespace as scopes and each layer continuing to use the previous key.\
Each translation key has to begin with a `.` and each translation will have to use the format `"<language key>": <translation>`\
You may surround the translation with `"` if you require a multiline translation\

If you have to comment something you can either use the language key `note` which will translate to a note element in the xlf files\
or use a `#` as a line comment

## Example file

```sass
source: en
product: exampleproduct

.apple
    en: "Apple"
    de: "Apfel"
    .peel
        note: "These translations will be listed as apple.peel"
        en: "peel"
        de: "Schale"

# This in an example comment
.example
    en: "Example"
    de: "Beispiel"
```