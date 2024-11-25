from aiogram.types import ReplyKeyboardMarkup, KeyboardButton

def make_row_keyboard_int(items: list[str]):
    row = [KeyboardButton(text=str(number+1)) for number in range(len(items))]
    return ReplyKeyboardMarkup(keyboard=[row], resize_keyboard=True)

def make_row_keyboard(items: list[str]):
    row = [KeyboardButton(text=item) for item in items]
    return ReplyKeyboardMarkup(keyboard=[row], resize_keyboard=True)
def list_to_text(items: list[str]):
    text = ""
    counter = 1
    for ii in items:
        text = text + str(counter) + ". " + ii + "\n"
        counter = counter + 1
    return text