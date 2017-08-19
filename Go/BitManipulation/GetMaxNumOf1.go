package AlgorithmGo

// Find the maximum number of consecutive 1
func GetMaxNumOf1(x uint64) int {
    count, max := 0, 0
    for x != 0 {
        if x & 1 != 0 {
            count++
        } else {
            count = 0
        }
        if max < count {
            max = count
        }
        x >>= 1
    }

    return max
}

