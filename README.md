## Fun with CSV

A repository containing code that does various stuff with CSV files.

### [CSVQuotes](./src/main/java/org/example/CSVQuotes.java)

Just a simple program that demonstrates how quotes need to be handled
in different scenarios in the context of a CSV file.

### [CSVReaderWriter](./src/main/java/org/example/util/CSVReaderWriter.java)

A simple CSV reader / writer implementation that works well on CSV input handling
various quirks neatly.

Some related resources:
- [The Only Class You Need for CSV Files | Agile Software Craftsmanship](https://agiletribe.wordpress.com/2012/11/23/the-only-class-you-need-for-csv-files/)
- [CSV parsing in Java - working example..?](https://stackoverflow.com/a/13655640/5614968)

### [SimpleCSVDemo](./src/main/java/org/example/SimpleCSVDemo.java)

A simple demo for the `SimpleCSVReaderWriter` class. It takes in a file
path as input and prints the object constructed from the CSV data in
the console. Further gets a new file path to which the CSV data needs to
be written back.

Input path for test data: `test-data/customers-1000.csv`

### Interesting resources

- [RFC 4180: Common Format and MIME Type for Comma-Separated Values (CSV) Files](https://www.rfc-editor.org/rfc/rfc4180)
- [unicode - What's the difference between UTF-8 and UTF-8 with BOM? - Stack Overflow](https://stackoverflow.com/q/2223882/5614968)
- **Dataset**: [Download Sample CSV Files for free - Datablist](https://www.datablist.com/learn/csv/download-sample-csv-files)
- [The Only Class You Need for CSV Files | Agile Software Craftsmanship](https://agiletribe.wordpress.com/2012/11/23/the-only-class-you-need-for-csv-files/)