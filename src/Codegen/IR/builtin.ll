; ModuleID = 'my.c'
source_filename = "my.c"
target datalayout = "e-m:o-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-apple-macosx10.15.0"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
; Function Attrs: noinline nounwind optnone ssp uwtable
define void @__builtin_print(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  ret void
}
declare i32 @printf(i8*, ...) #1
; Function Attrs: noinline nounwind optnone ssp uwtable
define void @__builtin_println(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %3)
  ret void
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define void @__builtin_printInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %3)
  ret void
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define void @__builtin_printlnInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret void
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define i32 @__builtin_getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}
declare i32 @scanf(i8*, ...) #1
; Function Attrs: noinline nounwind optnone ssp uwtable
define i8* @__builtin_getString() #0 {
  %1 = alloca i8*, align 8
  %2 = call i8* @malloc(i64 1024) #5
  store i8* %2, i8** %1, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  %5 = load i8*, i8** %1, align 8
  ret i8* %5
}
; Function Attrs: allocsize(0)
declare i8* @malloc(i64) #2
; Function Attrs: noinline nounwind optnone ssp uwtable
define i8* @__builtin_toString(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 8
  store i32 %0, i32* %2, align 4
  %4 = call i8* @malloc(i64 16) #5
  store i8* %4, i8** %3, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %3, align 8
  %7 = call i64 @llvm.objectsize.i64.p0i8(i8* %6, i1 false, i1 true, i1 false)
  %8 = load i32, i32* %2, align 4
  %9 = call i32 (i8*, i32, i64, i8*, ...) @__sprintf_chk(i8* %5, i32 0, i64 %7, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %8)
  %10 = load i8*, i8** %3, align 8
  ret i8* %10
}
declare i32 @__sprintf_chk(i8*, i32, i64, i8*, ...) #1
; Function Attrs: nounwind readnone speculatable willreturn
declare i64 @llvm.objectsize.i64.p0i8(i8*, i1 immarg, i1 immarg, i1 immarg) #3
; Function Attrs: noinline nounwind optnone ssp uwtable
define i32* @__builtin_malloc(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call i8* @malloc(i64 %4) #5
  %6 = bitcast i8* %5 to i32*
  ret i32* %6
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define i8* @__builtin_str_add(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %6 = load i8*, i8** %3, align 8
  %7 = call i64 @strlen(i8* %6)
  %8 = load i8*, i8** %4, align 8
  %9 = call i64 @strlen(i8* %8)
  %10 = add i64 %7, %9
  %11 = add i64 %10, 1
  %12 = mul i64 1, %11
  %13 = call i8* @malloc(i64 %12) #5
  store i8* %13, i8** %5, align 8
  %14 = load i8*, i8** %5, align 8
  %15 = load i8*, i8** %3, align 8
  %16 = load i8*, i8** %5, align 8
  %17 = call i64 @llvm.objectsize.i64.p0i8(i8* %16, i1 false, i1 true, i1 false)
  %18 = call i8* @__strcpy_chk(i8* %14, i8* %15, i64 %17) #6
  %19 = load i8*, i8** %5, align 8
  %20 = load i8*, i8** %4, align 8
  %21 = load i8*, i8** %5, align 8
  %22 = call i64 @llvm.objectsize.i64.p0i8(i8* %21, i1 false, i1 true, i1 false)
  %23 = call i8* @__strcat_chk(i8* %19, i8* %20, i64 %22) #6
  %24 = load i8*, i8** %5, align 8
  ret i8* %24
}
declare i64 @strlen(i8*) #1
; Function Attrs: nounwind
declare i8* @__strcpy_chk(i8*, i8*, i64) #4
; Function Attrs: nounwind
declare i8* @__strcat_chk(i8*, i8*, i64) #4
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_eq(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp eq i32 %7, 0
  ret i1 %8
}
declare i32 @strcmp(i8*, i8*) #1
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_ne(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp ne i32 %7, 0
  ret i1 %8
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_gt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp sgt i32 %7, 0
  ret i1 %8
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_ge(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp sge i32 %7, 0
  ret i1 %8
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_lt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp slt i32 %7, 0
  ret i1 %8
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define zeroext i1 @__builtin_str_le(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6)
  %8 = icmp sle i32 %7, 0
  ret i1 %8
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define i32 @__builtin_length(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i64 @strlen(i8* %3)
  %5 = trunc i64 %4 to i32
  ret i32 %5
}
; Function Attrs: noinline nounwind optnone ssp uwtable
define i8* @__builtin_substring(i8* %0, i32 %1, i32 %2) #0 {
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %8 = load i32, i32* %6, align 4
  %9 = load i32, i32* %5, align 4
  %10 = sub nsw i32 %8, %9
  %11 = add nsw i32 %10, 1
  %12 = sext i32 %11 to i64
  %13 = mul i64 1, %12
  %14 = call i8* @malloc(i64 %13) #5
  store i8* %14, i8** %7, align 8
  %15 = load i8*, i8** %7, align 8
  %16 = load i8*, i8** %4, align 8
  %17 = load i32, i32* %5, align 4
  %18 = sext i32 %17 to i64
  %19 = getelementptr inbounds i8, i8* %16, i64 %18
  %20 = load i32, i32* %6, align 4
  %21 = load i32, i32* %5, align 4
  %22 = sub nsw i32 %20, %21
  %23 = sext i32 %22 to i64
  %24 = mul i64 1, %23
  %25 = load i8*, i8** %7, align 8
  %26 = call i64 @llvm.objectsize.i64.p0i8(i8* %25, i1 false, i1 true, i1 false)
  %27 = call i8* @__memcpy_chk(i8* %15, i8* %19, i64 %24, i64 %26) #6
  %28 = load i8*, i8** %7, align 8
  %29 = load i32, i32* %6, align 4
  %30 = load i32, i32* %5, align 4
  %31 = sub nsw i32 %29, %30
  %32 = sext i32 %31 to i64
  %33 = getelementptr inbounds i8, i8* %28, i64 %32
  store i8 0, i8* %33, align 1
  %34 = load i8*, i8** %7, align 8
  ret i8* %34
}
; Function Attrs: nounwind
declare i8* @__memcpy_chk(i8*, i8*, i64, i64) #4
; Function Attrs: noinline nounwind optnone ssp uwtable
define i32 @__builtin_parseInt(i8* %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  %5 = call i32 (i8*, i8*, ...) @sscanf(i8* %4, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %3)
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}
declare i32 @sscanf(i8*, i8*, ...) #1
; Function Attrs: noinline nounwind optnone ssp uwtable
define i32 @__builtin_ord(i8* %0, i32 %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %4, align 4
  %7 = sext i32 %6 to i64
  %8 = getelementptr inbounds i8, i8* %5, i64 %7
  %9 = load i8, i8* %8, align 1
  %10 = sext i8 %9 to i32
  ret i32 %10
}

attributes #0 = { noinline nounwind optnone ssp uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "darwin-stkchk-strong-link" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "probe-stack"="___chkstk_darwin" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "darwin-stkchk-strong-link" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "probe-stack"="___chkstk_darwin" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { allocsize(0) "correctly-rounded-divide-sqrt-fp-math"="false" "darwin-stkchk-strong-link" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "probe-stack"="___chkstk_darwin" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readnone speculatable willreturn }
attributes #4 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "darwin-stkchk-strong-link" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "probe-stack"="___chkstk_darwin" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { allocsize(0) }
attributes #6 = { nounwind }

!llvm.module.flags = !{!0, !1, !2}
!llvm.ident = !{!3}

!0 = !{i32 2, !"SDK Version", [3 x i32] [i32 10, i32 15, i32 6]}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{i32 7, !"PIC Level", i32 2}
!3 = !{!"Apple clang version 12.0.0 (clang-1200.0.32.29)"}
