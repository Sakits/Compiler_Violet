declare i32 @printf(i8*, ...) #0

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
define void @__builtin_print(i8* %0) #0 {
__builtin_print:
  %1 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %0)
  ret void
}

@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
define void @__builtin_println(i8* %0) #0 {
__builtin_println:
  %1 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %0)
  ret void
}
