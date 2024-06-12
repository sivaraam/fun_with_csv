# Fun with CSV

A repository containing code that does various stuff with CSV files.

## [CsvQuotes](./src/main/java/org/example/CsvQuotes.java)

Just a simple program that demonstrates how quotes need to be handled
in different scenarios in the context of a CSV file.

## [CsvReaderWriter](./src/main/java/org/example/util/CsvReaderWriter.java)

A simple CSV reader / writer implementation that works well on CSV input handling
various quirks neatly.

Some related resources:
- [The Only Class You Need for CSV Files | Agile Software Craftsmanship](https://agiletribe.wordpress.com/2012/11/23/the-only-class-you-need-for-csv-files/)
- [CSV parsing in Java - working example..?](https://stackoverflow.com/a/13655640/5614968)

## [SimpleCsvDemo](./src/main/java/org/example/SimpleCsvDemo.java)

A simple demo for the `SimpleCsvReaderWriter` class. It takes in a file
path as input and prints the object constructed from the CSV data in
the console. Further gets a new file path to which the CSV data needs to
be written back.

Input path for test data: `test-data/customers-1000.csv`

### Run with Gradle

```shell
./gradlew runDemo
```

## Resources

- [RFC 4180: Common Format and MIME Type for Comma-Separated Values (CSV) Files](https://www.rfc-editor.org/rfc/rfc4180)
- [The Only Class You Need for CSV Files | Agile Software Craftsmanship](https://agiletribe.wordpress.com/2012/11/23/the-only-class-you-need-for-csv-files/)
- **Dataset**: [Download Sample CSV Files for free - Datablist](https://www.datablist.com/learn/csv/download-sample-csv-files)

### Faster processing

- [osiegmar/FastCSV: CSV library for Java that is fast, RFC-compliant and dependency-free.](https://github.com/osiegmar/FastCSV)

  [The benchmarks](https://github.com/osiegmar/FastCSV/blob/main/README.md#benchmark--compatibility)
  look interesting. It is said to be RFC 4180 compliant while outperforming many Java libraries in terms
  of the numbers of records it could process per second.

- [lemire/simdcsv: A fast SIMD parser for CSV files](https://github.com/lemire/simdcsv)

  Leverages SIMD operations to provide fast processing of CSV files. This is useful if we have
  the relevant hardware where this would perform well. This one is written in C++. 

  It might be interesting to explore similar offerings in Java. Alternatively, we could look for
  a bridge to this library.

### Interesting reads

- [unicode - What's the difference between UTF-8 and UTF-8 with BOM? - Stack Overflow](https://stackoverflow.com/q/2223882/5614968)
