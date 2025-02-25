# ANTLR Simple Grammar and Interpreter

## Installation

The recommended way to run this program is to create an IntelliJ project with the source files provided. Ensure that the ANTLR v4 plugin is installed on your version of IntelliJ. In addition to this, head to https://www.antlr.org/download.html and download the ANTLR .jar executable file. This will need to be linked IntelliJ via the following path: File > Project Structure > Libraries > Add New Library > Java > then select the executable file and apply.

## Parsing an Input

Provide a text file containing your input statements following the format as given in the example below:
    
    var x;
    x = (3 + 5) * 2;
    var y;
    y = x / 2;

The pat