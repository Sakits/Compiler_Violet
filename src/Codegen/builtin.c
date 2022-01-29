#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void __builtin_print(char* s)
{
    printf("%s", s);
}

void __builtin_println(char* s)
{
    printf("%s\n", s);
}

void __builtin_printInt(int x)
{
    printf("%d", x);
}

void __builtin_printlnInt(int x)
{
    printf("%d\n", x);
}

int __builtin_getInt()
{
    int x;
    scanf("%d", &x);
    return x;
}

char* __builtin_getString() 
{
    char* s = malloc(sizeof(char) << 10);
    scanf("%s", s);
    return s;
}

char* __builtin_toString(int x)
{
    char* s = malloc(sizeof(char) << 4);
    sprintf(s, "%d", x);
    return s;
}

int* __builtin_malloc(int size)
{
    return malloc(size);
}

char* __builtin_str_add(char* a, char* b)
{
    char* tmp = malloc(sizeof(char) * (strlen(a) + strlen(b) + 1));
    strcpy(tmp, a);
    strcat(tmp, b);
    return tmp;
}

bool __builtin_str_eq(char* a, char* b)
{
    return strcmp(a, b) == 0;
}

bool __builtin_str_ne(char* a, char *b)
{
    return strcmp(a, b) != 0;
}

bool __builtin_str_gt(char* a, char* b)
{
    return strcmp(a, b) > 0;
}

bool __builtin_str_ge(char* a, char* b)
{
    return strcmp(a, b) >= 0;
}

bool __builtin_str_lt(char* a, char* b)
{
    return strcmp(a, b) < 0;
}

bool __builtin_str_le(char* a, char* b)
{
    return strcmp(a, b) <= 0;
}

int __builtin_length(char* s)
{
    return strlen(s);
}

char* __builtin_substring(char* s, int l, int r) 
{
    char* a = malloc(sizeof(char) * (r - l + 1));
    memcpy(a, s + l, sizeof(char) * (r - l));
    a[r - l] = '\0';
    return a;
}

int __builtin_parseInt(char* s) 
{
    int x;
    sscanf(s, "%d", &x);
    return x;
}

int __builtin_ord(char* s, int pos)
{
    return s[pos];
}