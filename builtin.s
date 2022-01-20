	.file	"builtin.c"
	.text
	.section	.rodata.str1.4,"aMS",@progbits,1
	.align	2
.LC0:
	.string	"%s"
	.text
	.align	2
	.globl	__builtin_print
	.type	__builtin_print, @function
__builtin_print:
	mv	a1,a0
	lui	a0,%hi(.LC0)
	addi	a0,a0,%lo(.LC0)
	tail	printf
	.size	__builtin_print, .-__builtin_print
	.align	2
	.globl	__builtin_println
	.type	__builtin_println, @function
__builtin_println:
	tail	puts
	.size	__builtin_println, .-__builtin_println
	.section	.rodata.str1.4
	.align	2
.LC1:
	.string	"%d"
	.text
	.align	2
	.globl	__builtin_printInt
	.type	__builtin_printInt, @function
__builtin_printInt:
	mv	a1,a0
	lui	a0,%hi(.LC1)
	addi	a0,a0,%lo(.LC1)
	tail	printf
	.size	__builtin_printInt, .-__builtin_printInt
	.section	.rodata.str1.4
	.align	2
.LC2:
	.string	"%d\n"
	.text
	.align	2
	.globl	__builtin_printlnInt
	.type	__builtin_printlnInt, @function
__builtin_printlnInt:
	mv	a1,a0
	lui	a0,%hi(.LC2)
	addi	a0,a0,%lo(.LC2)
	tail	printf
	.size	__builtin_printlnInt, .-__builtin_printlnInt
	.align	2
	.globl	__builtin_getInt
	.type	__builtin_getInt, @function
__builtin_getInt:
	addi	sp,sp,-32
	lui	a0,%hi(.LC1)
	addi	a1,sp,12
	addi	a0,a0,%lo(.LC1)
	sw	ra,28(sp)
	call	scanf
	lw	ra,28(sp)
	lw	a0,12(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_getInt, .-__builtin_getInt
	.align	2
	.globl	__builtin_getString
	.type	__builtin_getString, @function
__builtin_getString:
	addi	sp,sp,-16
	li	a0,1024
	sw	ra,12(sp)
	sw	s0,8(sp)
	call	malloc
	mv	s0,a0
	mv	a1,a0
	lui	a0,%hi(.LC0)
	addi	a0,a0,%lo(.LC0)
	call	scanf
	lw	ra,12(sp)
	mv	a0,s0
	lw	s0,8(sp)
	addi	sp,sp,16
	jr	ra
	.size	__builtin_getString, .-__builtin_getString
	.align	2
	.globl	__builtin_toString
	.type	__builtin_toString, @function
__builtin_toString:
	addi	sp,sp,-16
	sw	s1,4(sp)
	mv	s1,a0
	li	a0,16
	sw	ra,12(sp)
	sw	s0,8(sp)
	call	malloc
	lui	a1,%hi(.LC1)
	mv	a2,s1
	addi	a1,a1,%lo(.LC1)
	mv	s0,a0
	call	sprintf
	lw	ra,12(sp)
	mv	a0,s0
	lw	s0,8(sp)
	lw	s1,4(sp)
	addi	sp,sp,16
	jr	ra
	.size	__builtin_toString, .-__builtin_toString
	.align	2
	.globl	__builtin_str_add
	.type	__builtin_str_add, @function
__builtin_str_add:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	sw	s1,20(sp)
	sw	s2,16(sp)
	sw	s3,12(sp)
	sw	s4,8(sp)
	mv	s2,a1
	mv	s4,a0
	call	strlen
	mv	s0,a0
	mv	a0,s2
	call	strlen
	mv	s3,a0
	add	a0,s0,a0
	addi	a0,a0,1
	call	malloc
	mv	a2,s0
	mv	a1,s4
	mv	s1,a0
	call	memcpy
	add	a0,s1,s0
	addi	a2,s3,1
	mv	a1,s2
	call	memcpy
	lw	ra,28(sp)
	lw	s0,24(sp)
	lw	s2,16(sp)
	lw	s3,12(sp)
	lw	s4,8(sp)
	mv	a0,s1
	lw	s1,20(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_add, .-__builtin_str_add
	.align	2
	.globl	__builtin_str_eq
	.type	__builtin_str_eq, @function
__builtin_str_eq:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	seqz	a0,a0
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_eq, .-__builtin_str_eq
	.align	2
	.globl	__builtin_str_ne
	.type	__builtin_str_ne, @function
__builtin_str_ne:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	snez	a0,a0
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_ne, .-__builtin_str_ne
	.align	2
	.globl	__builtin_str_gt
	.type	__builtin_str_gt, @function
__builtin_str_gt:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	sgt	a0,a0,zero
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_gt, .-__builtin_str_gt
	.align	2
	.globl	__builtin_str_ge
	.type	__builtin_str_ge, @function
__builtin_str_ge:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	not	a0,a0
	srli	a0,a0,31
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_ge, .-__builtin_str_ge
	.align	2
	.globl	__builtin_str_lt
	.type	__builtin_str_lt, @function
__builtin_str_lt:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	srli	a0,a0,31
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_lt, .-__builtin_str_lt
	.align	2
	.globl	__builtin_str_le
	.type	__builtin_str_le, @function
__builtin_str_le:
	addi	sp,sp,-16
	sw	ra,12(sp)
	call	strcmp
	lw	ra,12(sp)
	slti	a0,a0,1
	addi	sp,sp,16
	jr	ra
	.size	__builtin_str_le, .-__builtin_str_le
	.align	2
	.globl	__builtin_length
	.type	__builtin_length, @function
__builtin_length:
	tail	strlen
	.size	__builtin_length, .-__builtin_length
	.align	2
	.globl	__builtin_substring
	.type	__builtin_substring, @function
__builtin_substring:
	addi	sp,sp,-32
	sw	s0,24(sp)
	sub	s0,a2,a1
	sw	s3,12(sp)
	mv	s3,a0
	addi	a0,s0,1
	sw	ra,28(sp)
	sw	s1,20(sp)
	sw	s2,16(sp)
	mv	s2,a1
	call	malloc
	mv	s1,a0
	add	a1,s3,s2
	mv	a2,s0
	add	s0,s1,s0
	call	memcpy
	sb	zero,0(s0)
	lw	ra,28(sp)
	lw	s0,24(sp)
	lw	s2,16(sp)
	lw	s3,12(sp)
	mv	a0,s1
	lw	s1,20(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_substring, .-__builtin_substring
	.align	2
	.globl	__builtin_parseInt
	.type	__builtin_parseInt, @function
__builtin_parseInt:
	addi	sp,sp,-32
	lui	a1,%hi(.LC1)
	addi	a2,sp,12
	addi	a1,a1,%lo(.LC1)
	sw	ra,28(sp)
	call	sscanf
	lw	ra,28(sp)
	lw	a0,12(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_parseInt, .-__builtin_parseInt
	.align	2
	.globl	__builtin_ord
	.type	__builtin_ord, @function
__builtin_ord:
	add	a0,a0,a1
	lbu	a0,0(a0)
	ret
	.size	__builtin_ord, .-__builtin_ord
	.ident	"GCC: (GNU) 11.1.0"
