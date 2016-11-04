package main

import (
    "fmt"
)

func main() {
    s := "abcd"
    offset := 1
    fmt.Println(RotateStringRight1(s, offset))
    fmt.Println(RotateStringRight2(s, offset))
}

func RotateStringRight1(s string, offset int) string {
    if len(s) == 0 || offset <= 0 || offset % len(s) == 0 {
        return s
    }

    newS := make([]byte, len(s))
    offset %= len(s)
    for i := 0; i < len(s) - offset; i++ {
        newS[i + offset] = s[i]
    }
    for j := 0, i := len(s) - offset; i < len(s); i++, j++ {
        newS[j] = s[i]
    }

    return string(newS)
}

// Space Complexity: O(1)
func RotateStringRight2(s string, offset int) string {
    if len(s) == 0 || offset <= 0 || offset % len(s) == 0 {
        return s
    }

    offset %= len(s)
    s = reverse(s, 0, len(s) - offset - 1)
    s = reverse(s, len(s) - offset, len(s) - 1)
    s = reverse(s, 0, len(s) - 1)

    return s
}

func reverse(s string, start, end int) string {
    if len(s) <= 1 || end <= start || end > len(s) || start < 0 || end < 0 {
        return s
    }
    sArr := []rune(s)
    
    for i, j := start, end; i < j; i++, j-- {
        var temp rune = sArr[i]
        sArr[i] = sArr[j]
        sArr[j] = temp
    }

    return string(sArr)
}