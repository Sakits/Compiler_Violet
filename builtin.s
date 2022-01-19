	.file	"test.c"
	.option nopic
	.attribute arch, "rv32i2p0"
	.attribute unaligned_access, 0
	.attribute stack_align, 16
	.text
	.section	.rodata
	.align	2
.LC0:
	.string	"%s"
	.text
	.align	2
	.globl	__builtin_print
	.type	__builtin_print, @function
__builtin_print:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC0)
	addi	a0,a5,%lo(.LC0)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_print, .-__builtin_print
	.align	2
	.globl	__builtin_println
	.type	__builtin_println, @function
__builtin_println:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a0,-20(s0)
	call	puts
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_println, .-__builtin_println
	.section	.rodata
	.align	2
.LC1:
	.string	"%d"
	.text
	.align	2
	.globl	__builtin_printInt
	.type	__builtin_printInt, @function
__builtin_printInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC1)
	addi	a0,a5,%lo(.LC1)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_printInt, .-__builtin_printInt
	.section	.rodata
	.align	2
.LC2:
	.string	"%d\n"
	.text
	.align	2
	.globl	__builtin_printlnInt
	.type	__builtin_printlnInt, @function
__builtin_printlnInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC2)
	addi	a0,a5,%lo(.LC2)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_printlnInt, .-__builtin_printlnInt
	.align	2
	.globl	__builtin_getInt
	.type	__builtin_getInt, @function
__builtin_getInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	addi	a5,s0,-20
	mv	a1,a5
	lui	a5,%hi(.LC1)
	addi	a0,a5,%lo(.LC1)
	call	scanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_getInt, .-__builtin_getInt
	.align	2
	.globl	__builtin_getString
	.type	__builtin_getString, @function
__builtin_getString:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	li	a0,1024
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC0)
	addi	a0,a5,%lo(.LC0)
	call	scanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_getString, .-__builtin_getString
	.align	2
	.globl	__builtin_toString
	.type	__builtin_toString, @function
__builtin_toString:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	li	a0,16
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a2,-36(s0)
	lui	a5,%hi(.LC1)
	addi	a1,a5,%lo(.LC1)
	lw	a0,-20(s0)
	call	sprintf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	__builtin_toString, .-__builtin_toString
	.align	2
	.globl	malloc
	.type	malloc, @function
__builtin_str_add:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	sw	s1,36(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	sw	a1,-40(s0)
	lw	a0,-36(s0)
	call	strlen
	mv	s1,a0
	lw	a0,-40(s0)
	call	strlen
	mv	a5,a0
	add	a5,s1,a5
	addi	a5,a5,1
	mv	a0,a5
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a1,-36(s0)
	lw	a0,-20(s0)
	call	strcpy
	lw	a1,-40(s0)
	lw	a0,-20(s0)
	call	strcat
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	lw	s1,36(sp)
	addi	sp,sp,48
	jr	ra
	.size	__builtin_str_add, .-__builtin_str_add
	.align	2
	.globl	__builtin_str_eq
	.type	__builtin_str_eq, @function
__builtin_str_eq:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	seqz	a5,a5
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_eq, .-__builtin_str_eq
	.align	2
	.globl	__builtin_str_ne
	.type	__builtin_str_ne, @function
__builtin_str_ne:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	snez	a5,a5
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_ne, .-__builtin_str_ne
	.align	2
	.globl	__builtin_str_gt
	.type	__builtin_str_gt, @function
__builtin_str_gt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	sgt	a5,a5,zero
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_gt, .-__builtin_str_gt
	.align	2
	.globl	__builtin_str_ge
	.type	__builtin_str_ge, @function
__builtin_str_ge:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	not	a5,a5
	srli	a5,a5,31
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_ge, .-__builtin_str_ge
	.align	2
	.globl	__builtin_str_lt
	.type	__builtin_str_lt, @function
__builtin_str_lt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	srli	a5,a5,31
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_lt, .-__builtin_str_lt
	.align	2
	.globl	__builtin_str_le
	.type	__builtin_str_le, @function
__builtin_str_le:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	slti	a5,a5,1
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_str_le, .-__builtin_str_le
	.align	2
	.globl	__builtin_length
	.type	__builtin_length, @function
__builtin_length:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a0,-20(s0)
	call	strlen
	mv	a5,a0
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_length, .-__builtin_length
	.align	2
	.globl	__builtin_substring
	.type	__builtin_substring, @function
__builtin_substring:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	sw	a1,-40(s0)
	sw	a2,-44(s0)
	lw	a4,-44(s0)
	lw	a5,-40(s0)
	sub	a5,a4,a5
	addi	a5,a5,1
	mv	a0,a5
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a5,-40(s0)
	lw	a4,-36(s0)
	add	a3,a4,a5
	lw	a4,-44(s0)
	lw	a5,-40(s0)
	sub	a5,a4,a5
	mv	a2,a5
	mv	a1,a3
	lw	a0,-20(s0)
	call	memcpy
	lw	a4,-44(s0)
	lw	a5,-40(s0)
	sub	a5,a4,a5
	mv	a4,a5
	lw	a5,-20(s0)
	add	a5,a5,a4
	sb	zero,0(a5)
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	__builtin_substring, .-__builtin_substring
	.align	2
	.globl	__builtin_parseInt
	.type	__builtin_parseInt, @function
__builtin_parseInt:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	addi	a5,s0,-20
	mv	a2,a5
	lui	a5,%hi(.LC1)
	addi	a1,a5,%lo(.LC1)
	lw	a0,-36(s0)
	call	sscanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	__builtin_parseInt, .-__builtin_parseInt
	.align	2
	.globl	__builtin_ord
	.type	__builtin_ord, @function
__builtin_ord:
	addi	sp,sp,-32
	sw	s0,28(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a5,-24(s0)
	lw	a4,-20(s0)
	add	a5,a4,a5
	lbu	a5,0(a5)
	mv	a0,a5
	lw	s0,28(sp)
	addi	sp,sp,32
	jr	ra
	.size	__builtin_ord, .-__builtin_ord
	.ident	"GCC: (GNU) 10.2.0"
