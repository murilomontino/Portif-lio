def fmt_input(msg='', typeWord=int):
    if typeWord==str:
        phrase = input(msg)
        return phrase
    else:
        phrase = input(msg).split()
        phrase = [ typeWord(word) for word in phrase ]
        return phrase


