






; paramaters of assignment
; function titled sortList with paramaters:
; address of 1st element in list, number items in list 
; two lists: sorted list, and waiting to sort list

; program functions
; sort list in asending order
; print function.




; assumptions of assignment
; number list exists at location l, element count at location n


; store number of items in list in register
; store address of first item in memory so it wont change
; store address of first item in register for list pointer operation
; allocate a register for iterations (count)
; allocate a register or memory address valued at 0, label sCount

ascending:
; arrange ascending

; outer loop: (exit condition: sCount = 0 at end of middle loop

; set count = 0 and memory pointer = l) at top of outer loop

; inner loop (exit condition, count = n - 1 (logic iteration ends 
; at list entry 2nd from end (last compare = 2nd to last number and
; last #)

; store values at n and n + 1, if n > n + 1, save n and n + 1
; to swapped addresses
; count++
; address pointer++

descending:
; descending is same as ascending, but higher value goes to address at pointer
; instead of the lower one going to pointer


print:
; printOut fn with paramaters address of list and num items
 


main:
; logic call print to display unsorted list
; sort it ascending
; print result


