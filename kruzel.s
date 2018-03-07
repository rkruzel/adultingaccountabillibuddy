# Author Name: Robert Kruzel
# Last Modified Date: 10/3/2017
# Compilation Status:
# Running Status:
# Test Results
# Run 1
#
# Run 2
# 
# Run 3
# 
# Run 4
#

.data
arrayData:
		.word	6060

spaceAscii:	.asciiz " "


##############################################################################
# Main, print unsorted list, sort list, print sorted list, exit ##############
##############################################################################
.text
.globl main
	
	
main:

	la 		$a1, arrayData		#pointer to arrayData
	la		$a2, spaceAscii		#ascii conversion of char 'space'
	li 		$a3, 10				#size of array defined by program requirements

	jal		printOut			#always jump to printOut to print pre-sort
	jal		sortList			#always jump to sort
	jal 	printOut			#always jump to printOut to print sorted list
	
	li		$v0, 10				#set exit flag
	syscall						#execute exit
	
	
##############################################################################
# Function sortList: if an item in the list is larger then the next, switch thier values in memory.
# iterate until no swaps need to be made.
##############################################################################

.text
.globl sortList	
	
sortList:

	move	$s0,$a1 		#stores array pointer to s0
	move	$s1,$a3			#stores size to s1
	li		$s2, 0			#register for number of swaps
	
	
OuterLoop:
	li		$s3,0				#sets loop count to 0 in s3
	
	
InnerLoop:
	
	lw		$t0,0($s0)			#t0 is value at array pointer
	lw		$t1,4($s0)			#t1 is next value from array pointer
	bge		$t1,$t0,skip		#if t1 >= t0, then no need to swap
	sw 		$t1,0($s0)			#store swapped values to each other's initial address
	sw		$t0,4($s0)	
	addi	$s2,1				#increment swap count
skip:
	addi    $s3, 1				#incriment loop count (done before compare to end on iteration size - 1
	beq		$s3,$s1,exitInner	#exit inner loop when count = size 
	addi	$s0,4				#incriment array pointer
exitInner:
	beq		$s2,0,exitOuter		#exits outer loop if no swap events happened
	move	$s0,$a1				#if any swap occured reset array pointer and iterate again
	j		OuterLoop			
	
exitOuter:
	jr 		$ra					#return to main


##############################################################################
# Function printOut: iterates through array once printing values at memory locations
# assumed size != 0, gets passed pointer to start of array (#a1) and size($a3)
##############################################################################
.text
.globl printOut

printOut:
	la		$s0,arrayData		#copy address of array to register
	li		$s1,10				#copy size
	li		$t1,0				#loop count

printLoop:
	lw		$t0,0($s0)			#get value at pointer
	li		$v0,1				#set syscall flag for print int
	move	$a0, $t0			#set value to print
	syscall						#call print
	addi	$t1,$t1,1			#increment loop count
	addi	$s0,$s0,4			#move pointer to next word
	beq		$a3,$t1,done		#exit condition count = size
	move 	$a0, $a2			#print space for formatting
	syscall
	j 		printLoop			#if count != size, iterate again
	
done:
	jr		$ra					#return to main

 





