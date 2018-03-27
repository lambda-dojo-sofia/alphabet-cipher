let keyword = "vigilance" ;;
let plaintext = "meetmeontuesdayeveningatseven" ;;

let findChar keyword i = keyword.[i mod String.length keyword] ;;

let intToLetter i = Char.chr (i + Char.code 'a')
let letterToInt l = Char.code l - Char.code 'a'
let shiftChar a b = intToLetter ((letterToInt a) + (letterToInt b)) mod 26

let shift keyword i c = shiftChar (findChar keyword i) c ;;

let encrypt keyword plaintext = String.mapi (shift keyword) plaintext ;;

let () = print_endline (encrypt keyword plaintext) ;;
