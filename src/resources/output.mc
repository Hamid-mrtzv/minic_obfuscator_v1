int v0(int v1, int v2) {
    int _confuse0 = 1;
    while (_confuse0 < 6) {
        _confuse0++;
    }

    int v3 = v1 + v2;
    return v3;
}
int main() {
    int _confuse1 = 2;
    while (_confuse1 < 7) {
        _confuse1++;
    }

    int v4 = 0;
    while (1) {
        int _confuse2 = 10;
        while (_confuse2 < 15) {
            _confuse2++;
        }

        switch (v4) {
            int _confuse3 = 7;
            while (_confuse3 < 12) {
                _confuse3++;
            }

            case 0:
            int v5 = 3;
            v4 = 1;
            break;
            case 1:
            int v6 = 4;
            v4 = 2;
            break;
            case 2:
            int v7 = v0(v5, v6);
            v4 = 3;
            break;
            case 3:
            printf("%d\n", v7);
            v4 = 4;
            break;
            case 4:
            // original: return 0;
            return 0;
            default:
            return 0;
        }
    }
}
