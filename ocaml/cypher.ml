let findChar keyword i = keyword.[i mod String.length keyword]

let wrapAround n = match n with
    | _ when n < 0 -> (n + 26)
    | _ -> n mod 26

let intToLetter i = Char.chr ((wrapAround i) + Char.code 'a')
let letterToInt l = Char.code l - Char.code 'a'

let shiftChar direction a b =
        intToLetter ((letterToInt a) * direction + (letterToInt b))

let shift direction keyword i c = shiftChar direction (findChar keyword i) c

let cryptomagic fn keyword plaintext = String.mapi (fn keyword) plaintext

let encrypt = cryptomagic (shift   1 )
let decrypt = cryptomagic (shift (-1))

let prompt message =
    print_string message;
    read_line ()

let getMode = match (prompt "Mode? [e|d] ") with
    | "e" -> encrypt
    | _   -> decrypt

let () =
    let fn = getMode in
    let keyword = prompt "Keyword? " in
    let message = prompt "Message? " in
    print_endline (fn keyword message)
