package main
import "os"
import "fmt"
import "strings"
import "regexp"

func main() {
    reg, err := regexp.Compile("[^a-z]+")

    if err != nil {
        fmt.Println(err)
    }

    operation := os.Args[1]
    message := reg.ReplaceAllString(strings.ToLower(os.Args[2]), "")
    cipher := reg.ReplaceAllString(strings.ToLower(os.Args[3]), "")

    fmt.Println(message)


    if operation == "0" {
        fmt.Println("Operation: encipher")
    } else {
        fmt.Println("Operation: decipher")
    } 

    fmt.Println("Input message: " + message)
    
    output := ""
    for i, j := 0, 0; i < len(message); i, j = i + 1, j + 1 {
        if operation == "0" {
            output += string(padChar(cipher[j], message[i]))
        } else {
            output += string(unpadChar(cipher[j], message[i]))
        }
        if j == len(cipher) - 1 {
            j = -1
        }
    }
    fmt.Println(output)
}

func padChar(column byte, row byte) byte {
    result := column + (row - 97)
    if result > 97 + 26 {
        result -= 26
    }
    return result
}

func unpadChar(column byte, row byte) byte {
    result := row - (column - 97)
    if result < 97 {
        result += 26
    }
    return result
}