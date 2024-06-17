# DNA Compression and Decompression

This Java program provides functionality for compressing and decompressing DNA sequences. The sequences are represented using binary and hexadecimal formats for efficient storage and processing.

## Features

- **Compression (`comp`)**: Converts a DNA sequence into its binary representation and then into a hexadecimal format.
- **Decompression (`decomp`)**: Converts a hexadecimal representation back into its binary format and then to the original DNA sequence.
- **About Information**: Displays the author and group information.

## Usage

### Compression

To compress a DNA sequence, use the `comp` command followed by the sequence.

#### Example

```
comp ACGT
```

#### Output

```
4 01 23
```

### Decompression

To decompress a sequence, use the `decomp` command followed by the length of the original sequence and the hexadecimal representation.

#### Example

```
decomp 4 01 23
```

#### Output

```
01 23 
ACGT
```

### About

To display author and group information, use the `about` command.

```
about
```

#### Output

```
Author: Džeina Bīskapa
```

### Exit

To exit the program, type `exit`.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your system.
- A code editor or IDE (such as IntelliJ IDEA or Eclipse).

### Input Commands

The program accepts input commands from the user. The supported commands are `comp`, `decomp`, `about`, and `exit`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
