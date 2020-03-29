# NBPParser
Program which interacts with NBP API
Based on arguments:
- currency code (USD, EUR, CHF, GBP)
- start date
- end date both in YYYY-MM-DD formats

prints out basic average and standard deviation of a given period

Technologies used:
- Java
- Maven

For example run
java pl.nbpparser.MainClass CHF 2020-01-02 2020-01-31

Output would be:
CHF > currency code
2020-01-02 > start date
2020-01-31 > end date
3.9495425 > average rate
0.03312773 > standard deviation

