# Differ

### Hexlet tests and linter status:
[![Actions Status](https://github.com/paulvino/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/paulvino/java-project-71/actions)
[![Actions Status](https://github.com/paulvino/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/paulvino/java-project-71/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/995f2c889eff35789c4e/maintainability)](https://codeclimate.com/github/paulvino/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/995f2c889eff35789c4e/test_coverage)](https://codeclimate.com/github/paulvino/java-project-71/test_coverage)

### Description:
Fast, small and simple CLI-application to compare content of two configurations files. 
The result of the comparison will be displayed on the screen after the operation is completed.

### Supported file formats:
  - .json;
  - .yml (.yaml)

### Supported result formats:
  - stylish (default)
  - plain text
  - json

### How to install:
    git clone git@github.com:paulvino/java-project-71.git
    cd java-project-71/app
    make -C app build

### Reference:
    make help

### Version:
    make version

### Run (default result format):
    make compare-json

### Run (change result format):
    make compare-f-format

### Examples of usage (in asciinema format):
#### Final demo:
[![asciicast](https://asciinema.org/a/631qCkOzFO6oJZzJtE2ENYEq4.svg)](https://asciinema.org/a/631qCkOzFO6oJZzJtE2ENYEq4)

#### Help run:
[![asciicast](https://asciinema.org/a/ZPOX6NhVSd3lsTnlAi72zMe7o.svg)](https://asciinema.org/a/ZPOX6NhVSd3lsTnlAi72zMe7o)

#### Comparing two .json files:
[![asciicast](https://asciinema.org/a/3VjYuVMxeXjZzvHBQG07Q0h8u.svg)](https://asciinema.org/a/3VjYuVMxeXjZzvHBQG07Q0h8u)

#### Comparing two .json files, plain text result:
[![asciicast](https://asciinema.org/a/hH8mLKnEFN4j86BaWhk2MhsoN.svg)](https://asciinema.org/a/hH8mLKnEFN4j86BaWhk2MhsoN)

#### Comparing two .yml files:
[![asciicast](https://asciinema.org/a/1iFdzFHNqID9X86kfPfCnMuox.svg)](https://asciinema.org/a/1iFdzFHNqID9X86kfPfCnMuox)

#### Comparing two .json files, json result:
[![asciicast](https://asciinema.org/a/O8C9cNLVsBE6k3tpBACX4U4Xf.svg)](https://asciinema.org/a/O8C9cNLVsBE6k3tpBACX4U4Xf)