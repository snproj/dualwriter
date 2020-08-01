# dualwriter
Hello, writers; this is a terrible, very makeshift program for writing in a double-author structure using a laughably basic notation. Outputs in HTML (browser format) of all things.

## What you'll need
Things included which you'll need to download and use, but you don't have to care about how they work:
  1. dualp.jar file (stands for dualwriter-parser)
  2. "head" file
  3. "tail" file
  
Things not included which you'll need to use, but you don't have to care about how they work:
  1. Java (you might already have this)

## Usage
Steps:
  1. Write your text file using the markup notation (detailed later).
  2. Put that text file, the dualp.jar file, the "head" file and the "tail" file all in one folder (I'll call mine MyFolder).
  3.
  ```bash
  # Let's say the name of the file you wanna output is myFile.html
  java -jar dualp.jar head tail myFile.html
  ```
  
  A new file called myFile.html should appear in the same folder. Now, because it's an .html file (the same kind of file all browsers view), you need to open myFile.html in your browser. So...
  4. Open whatever browser you have
  5. Key into the address bar:
  ```bash
  C:\MyFolder\myFile.html
  ```
  
  This is called the path to myFile.html.
  6. That's all. You should now see the document in dual-writer format in your browser. Of course, this software is crappily written, so if it doesn't work, it might not be your fault lol
  
  
## Markup Notation
### \\\\1
Write for the first person. (Justified and shifted to the left of the page.)
For example:
```bash
\\1This is the first person speaking.
```

### \\\\2
Write for the second person. (Justified and shifted to the right of the page.)
For example:
```bash
\\2This is the second person speaking.
```

### \\\\s
Write text in the middle of the two (Not justified, shrunk to the middle of the page.)
By the way, 's' stands for "simul" (because I thought of it like two people speaking together).
```bash
\\sNow we're talking together.
```

### \\\\d
Ok, this is also for two people speaking together, but each saying their own thing.
The text belonging to each person will lean to their respective sides.

In order to denote which text belongs to which person, you use this in conjunction with the below two sub-notations, namely:
  @@a for the first person, and
  @@b for the second person.

For example:
```bash
\\d@@aI want to talk! Stop talking over me!
@@bI'm still talking... Blah blah blah.
```

## Problems
Watch out for:
  - Every space is counted as part of the main text body (you've probably realized there's no spaces in `\\1Hello`, for example)
  - Every newline is counted, or at least does affect the .html file (so technically, `\\d@@aI want to talk! Stop talking over me!@@bI'm still talking... Blah blah blah.` should be exactly like that, with no newlines nor spaces between speakers, but meh readability matters too... idk you decide. At this point, I'm not even sure browsers really take note of this slight difference.)
  - I haven't yet made escape sequences if by any chance you need to write "\\\\" or "@@" as part of your passage body itself for some reason lol
  
## License & Contributing
See LICENSE.md
  
