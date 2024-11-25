import asyncio
import logging
import requests
from aiogram import Router
from aiogram import Bot, Dispatcher, types
from aiogram.fsm.state import StatesGroup, State
from aiogram.fsm.context import FSMContext
from aiogram.filters.command import Command
from aiogram.utils.keyboard import ReplyKeyboardBuilder
from aiogram import F
from keyboard_constructor import make_row_keyboard
from keyboard_constructor import make_row_keyboard_int
from keyboard_constructor import list_to_text

Disciplines = ["Матанализ", "Физика", "Экономика"]
Groups_in = ["Б23-534"]
Groups_out = ["Б23-514", "С23-439", "Б22-666"]


class DecisionTree(StatesGroup):
    Start = State()
    GroupList = State()
    AddToGroup = State()
    ChooseAction = State()
    ChooseActionGroup = State()
    ChooseActionResults = State()
    ChooseGroup = State()
    ChooseCompletedTest = State()
    ChooseGroupIn = State()
    ChooseGroupOut = State()
    ChooseDisciplines = State()
    Unsubscribe = State()
    Subscribe = State()
    ChooseDiscipline = State()
    StartTest = State()


router = Router()

@router.message(Command("start"))
async def cmd_start(message: types.Message, state: FSMContext):
    await (message.answer
    (
        text = "Выбор действия:\n1. Просмотр списка групп\n"
               "2. Просмотр результатов тестов\n3. Выбор дисциплины",
        reply_markup = make_row_keyboard(['1', '2', '3'])
    ))
    await state.set_state(DecisionTree.ChooseAction)

@router.message(DecisionTree.Start)
async def cmd_start(message: types.Message, state: FSMContext):
    await(message.answer
        (
        text="Выбор действия:\n1. Просмотр списка групп\n"
             "2. Просмотр результатов тестов\n3. Выбор дисциплины",
        reply_markup=make_row_keyboard(['1', '2', '3'])
    ))
    await state.set_state(DecisionTree.ChooseAction)

@router.message(DecisionTree.ChooseAction)
async def choose_action(message: types.Message, state: FSMContext):
    await state.update_data(ChooseAction=message.text.lower())
    user_data = await state.get_data()
    if (message.text.lower() == '1'):
        await (message.answer
            (
            text = "Просмотр списка групп:\n1. Выбор группы\n"
                 "2. Добавление в новую группу\n3. Главное меню",
            reply_markup = make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseActionGroup)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text = "Просмотр результатов тестов:\n1. Продолжить незаконченный тест\n"
                   "2. Посмотреть результаты законченного\n3. Главное меню",
            reply_markup = make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseActionResults)

    elif (message.text.lower() == '3'):
        await (message.answer
            (
            text = list_to_text(Disciplines),
            reply_markup = make_row_keyboard_int(Disciplines)
        ))
        await state.set_state(DecisionTree.ChooseDisciplines)

    else:
        await (message.answer
            (
            text = "Выберите верную команду",
        ))

@router.message(DecisionTree.ChooseActionGroup)
async def answer_chosen(message: types.Message, state: FSMContext):
    await state.update_data(ChooseActionGroup=message.text.lower())
    user_data = await state.get_data()
    if (message.text.lower() == '1'):
        await (message.answer
            (
                text = "Группы в которых вы состоите\n" + list_to_text(Groups_in),
                reply_markup = make_row_keyboard_int(Groups_in)
            ))
        await state.set_state(DecisionTree.ChooseGroupIn)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text = "Группы в которых вы не состоите\n" + list_to_text(Groups_out),
            reply_markup = make_row_keyboard_int(Groups_out)
        ))
        await state.set_state(DecisionTree.ChooseGroupOut)

    elif (message.text.lower() == '3'):
        await state.clear()
        await state.set_state(DecisionTree.Start)

    else:
        await (message.answer
            (
            text = "Выберите верную команду",
        ))

@router.message(DecisionTree.ChooseDisciplines)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (int(message.text.lower()) > len(Disciplines)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        await state.update_data(ChooseDisciplines=Disciplines[int(message.text.lower())-1])
        await (message.answer
            (
            text = Disciplines[int(message.text.lower())-1] + ": [СПИСОК ТЕСТОВ]",
            reply_markup = make_row_keyboard(['1', '2'])
        ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

@router.message(DecisionTree.ChooseGroupIn)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (int(message.text.lower()) > len(Groups_in)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        await state.update_data(ChooseDisciplines=Groups_in[int(message.text.lower())-1])
        await (message.answer
            (
            text = "Вы хотите отписаться от группы " + Groups_in[int(message.text.lower())-1] + "?\n1. Подтвердить\n2. Отмена",
            reply_markup = make_row_keyboard(['1', '2'])
        ))
        await state.set_state(DecisionTree.Unsubscribe)

@router.message(DecisionTree.Unsubscribe)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (message.text.lower() == '1'):
        await (message.answer
            (
                text = "Вы не отписались от группы, так как эта функция не реализована\n"
                       "Нажмите любую кнопку для перехода в главное меню"
            ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text="Нажмите любую кнопку для перехода в главное меню"
        ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

@router.message(DecisionTree.ChooseGroupOut)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (int(message.text.lower()) > len(Groups_out)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        await state.update_data(ChooseDisciplines=Groups_out[int(message.text.lower())-1])
        await (message.answer
            (
            text = "Вы хотите подписаться на группу " + Groups_out[int(message.text.lower())-1] + "?\n1. Подтвердить\n2. Отмена",
            reply_markup = make_row_keyboard(['1', '2'])
        ))
        await state.set_state(DecisionTree.Subscribe)

@router.message(DecisionTree.Subscribe)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (message.text.lower() == '1'):
        await (message.answer
            (
                text = "Вы не подписались на группу, так как эта функция не реализована.\n"
                       "Нажмите любую кнопку для перехода в главное меню"
            ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text = "Нажмите любую кнопку для перехода в главное меню"
        ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

@router.message(DecisionTree.ChooseActionResults)
async def answer_chosen(message: types.Message, state: FSMContext):
    await state.update_data(ChooseActionGroup=message.text.lower())
    user_data = await state.get_data()
    if (message.text.lower() == '1'):
        await (message.answer
            (
                text = "[СПИСОК НЕЗАКОНЧЕННЫХ ТЕСТОВ]\n",
                reply_markup = make_row_keyboard_int(Groups_in)
            ))
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text = "[СПИСОК РЕЗУЛЬТАТОВ]\n",
            reply_markup = make_row_keyboard_int(Groups_out)
        ))
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '3'):
        await state.clear()
        await state.set_state(DecisionTree.Start)

    else:
        await (message.answer
            (
            text = "Выберите верную команду",
        ))