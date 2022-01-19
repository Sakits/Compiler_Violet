	.text

	.globl	__builtin_init
	.p2align	2
	.type	__builtin_init,@function
__builtin_init:
__builtin_init_init:
	addi	sp, sp, -4
	mv	s0, ra
	sw	s0, 0(sp)
	j	__builtin_init_1:
__builtin_init_1:
	lw	s0, 0(sp)
	mv	ra, s0
	addi	sp, sp, 4
	ret
	.size	__builtin_init, .-__builtin_init
	.globl	main
	.p2align	2
	.type	main,@function
main:
main_init:
	addi	sp, sp, -4
	mv	s0, ra
	sw	s0, 0(sp)
	j	main_1:
main_1:
	call	__builtin_init:
	mv	a0, zero
	lw	s0, 0(sp)
	mv	ra, s0
	addi	sp, sp, 4
	ret
	.size	main, .-main

	.section	.sdata,"aw",@progbits
