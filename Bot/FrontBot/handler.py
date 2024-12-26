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
from keyboard_constructor import make_row_keyboard_arrows
from Bot.backBot import entities as en
import test_data_base as tdb 

Groups_in = ["Б23-534"]
Groups_out = ["Б24-439", "С22-666"]

test_disc1 = tdb.Discipline("Матан")
test_disc2 = tdb.Discipline("Экономика")
test_disc3 = tdb.Discipline("Сопромат")
test_ans1 = tdb.Answer("4", "")
test_ans2 = tdb.Answer("да", "")
test_ans3 = tdb.Answer("экономика", "")
test_ans4 = tdb.Answer("карл маркс", "")
test_ans5 = tdb.Answer("б", "")
test_ans6 = tdb.Answer("в", "")
test_ans7 = tdb.Answer("а", "")
test_ans8 = tdb.Answer("да", "")
test_ans9 = tdb.Answer("нет", "")
test_ans10 = tdb.Answer("бесконечность", "")
test_ans11 = tdb.Answer("25", "")
test_ans12 = tdb.Answer("42", "")
test_ques1 = tdb.Question("2+2=?", 1, [test_ans1], ["3", "4", "66"], 0)
test_ques2 = tdb.Question("Довгий крут?", 1, [test_ans2], ["Да", "Нет"], 0)
test_ques3 = tdb.Question("Экономика или эхо гномика?", 1, [test_ans3], ["Экономика", "Эхо гномика?"], 0)
test_ques4 = tdb.Question("Кто написал Капитал?", 1, [test_ans4], ["Карл Маркс", "Фридрих Ницше", "Тутмос III"], 0)
test_ques5 = tdb.Question("Хз, что такое сопромат, ткни кнопку Б", 1, [test_ans5], ["А", "Б", "В"], 0)
test_ques6 = tdb.Question("A теперь ткни В", 1, [test_ans6], ["А", "Б", "В"], 0)
test_ques7 = tdb.Question("Напоследок ткни A", 1, [test_ans7], ["А", "Б", "В"], 0)
test_ques8 = tdb.Question("5 = 5?", 1, [test_ans8], ["Да", "Нет"], 0)
test_ques9 = tdb.Question("7 = 1?", 1, [test_ans9], ["Да", "Нет"], 0)
test_ques10 = tdb.Question("Предел n+1 (n->беск)?", 1, [test_ans10], ["0", "1", "Бесконечость"], 0)
test_ques11 = tdb.Question("5*5=?", 1, [test_ans11], ["25", "8"], 0)
test_ques12 = tdb.Question("В чём смысл жизни?", 1, [test_ans12], ["99", "42", "???"], 0)
test_test1 = tdb.Test("Короткий тест", 0, 0, [test_ques1, test_ques2], test_disc1)
test_test4 = tdb.Test("Длинный тест", 0, 0, [test_ques8, test_ques9, test_ques10, test_ques11, test_ques12], test_disc1)
test_test2 = tdb.Test("Экон.тест", 0, 0, [test_ques3, test_ques4], test_disc2)
test_test3 = tdb.Test("Сопр.тест", 0, 0, [test_ques5, test_ques6, test_ques7], test_disc3)

test_student = en.Student("Иван","Иванов", "Ivan", 000, [1, 0], 534, 111)
test_sub = en.StudentSub(1, 111, 534, 11)
test_univ = en.University("МИФИ", [111], 000)
test_party1 = en.Party("Б23-534", [42], [7, 8], [111], 534)
test_party2 = en.Party("Б23-439", [422], [8, 9], [], 439)
test_party3 = en.Party("Б23-534", [4222], [7, 9], [], 666)
test_res1 = en.Result(0, 0, 111, 12, [201, 202], 313)
test_res2 = en.Result(0, 0, 111, 14, [203, 204], 323)
test_res3 = en.Result(0, 0, 111, 16, [205], 333)



Array_tests = [test_test1, test_test2, test_test3, test_test4]
Array_ques = [test_ques1, test_ques2, test_ques3, test_ques4, test_ques5, test_ques6,
              test_ques7, test_ques8, test_ques9, test_ques10, test_ques11, test_ques12]
Array_res = [test_res1, test_res2, test_res3]
Array_ans = [test_ans1, test_ans2, test_ans3, test_ans4, test_ans5, test_ans6,
             test_ans7, test_ans8, test_ans9, test_ans10, test_ans11, test_ans12]
Array_disc = [test_disc1, test_disc2, test_disc3]

class DecisionTree(StatesGroup):
    Start = State()
    GroupList = State()
    ChooseAction = State()
    ChooseActionGroup = State()
    ChooseActionResults = State()
    ChooseGroupIn = State()
    ChooseGroupOut = State()
    ChooseDisciplines = State()
    Discipline = State()
    Unsubscribe = State()
    Subscribe = State()
    FirstQuestion = State()
    SecondQuestion = State()
    EndTest = State()
    CurrentTest = State()
    TestQuestions = State()
    Questions = State()
    IsAnswered = State()
    Length = State()

router = Router()

@router.message(Command("start"))
async def cmd_start(message: types.Message, state: FSMContext):
    await (message.answer
    (
        text = "Короче, ты пока " + test_student.firstName + " " + test_student.lastName
               + " c ником " + test_student.nickname + "\n Это заглушка, Алексей потом свяжет с БД\n"
               + "Выбор действия:\n1. Просмотр списка групп\n"
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
                   "2. Посмотреть результаты\n3. Главное меню",
            reply_markup = make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseActionResults)

    elif (message.text.lower() == '3'):
        #Current_disc = GetDisciplineByID(disciplineId)

        Current_disc = []
        text_message = ""
        ii = 0
        for item in Array_disc:
            ii = ii + 1
            Current_disc.append(item)
            text_message = text_message + str(ii) + ". " + item.name + "\n"

        await (message.answer
            (
            text = text_message,
            reply_markup = make_row_keyboard_int(Current_disc)
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

    Current_disc = []
    for item in Array_disc:
        Current_disc.append(item)

    if (int(message.text.lower()) > len(Current_disc)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:

        #Current_tests = GetTestByDiscipline(disciplineId)
        
        await state.update_data(ChooseDisciplines=Current_disc[int(message.text.lower())-1])
        user_data = await state.get_data()
        Current_tests = []
        ii = 0
        text_message = ""
        for item in Array_tests:
            if (item.discipline == user_data['ChooseDisciplines']):
                ii = ii + 1
                Current_tests.append(item)
                text_message = (text_message + str(ii) +  ". " + item.testName + "\n")
        await state.update_data(CurrentTests=Current_tests)

        await (message.answer
            (
            text = user_data['ChooseDisciplines'].name + ": \n" + text_message,
            reply_markup = make_row_keyboard_int(Current_tests)
        ))
        await state.set_state(DecisionTree.FirstQuestion)

@router.message(DecisionTree.ChooseGroupIn)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (int(message.text.lower()) > len(Groups_in)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        await state.update_data(ChooseGroupIn=Groups_in[int(message.text.lower())-1])
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
            ))
        await (message.answer
            (
                text = "Выбор действия:\n1. Просмотр списка групп\n"
                       "2. Просмотр результатов тестов\n3. Выбор дисциплины",
                reply_markup = make_row_keyboard(['1', '2', '3'])
            ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup = make_row_keyboard(['1', '2', '3'])
        ))
        await state.clear()
        await state.set_state(DecisionTree.ChooseAction)

@router.message(DecisionTree.ChooseGroupOut)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (int(message.text.lower()) > len(Groups_out)):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        await state.update_data(ChooseGroupOut=Groups_out[int(message.text.lower())-1])
        await (message.answer
            (
            text = "Вы хотите подписаться на группу " + Groups_out[int(message.text.lower())-1]+ "?\n1. Подтвердить\n2. Отмена",
            reply_markup = make_row_keyboard(['1', '2'])
        ))
        await state.set_state(DecisionTree.Subscribe)

@router.message(DecisionTree.Subscribe)
async def answer_chosen(message: types.Message, state: FSMContext):
    if (message.text.lower() == '1'):
        await (message.answer
            (
                text = "Вы не подписались на группу, так как эта функция не реализована.\n"
            ))
        await (message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup = make_row_keyboard(['1', '2', '3'])
        ))
        await state.clear()
        await state.set_state(DecisionTree.Start)

    elif (message.text.lower() == '2'):
        await (message.answer
            (
            text = "Нажмите любую кнопку для перехода в главное меню"
        ))
        await (message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup = make_row_keyboard(['1', '2', '3'])
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
            ))
        await(message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup=make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseAction)

    elif (message.text.lower() == '2'):

        #Current_res = []
        #for item in Array_res:
            #for party in item.partys:
                #if (party == test_student.subs):
                    #Current_res.append(item)
        res_text = '' # переделать в первую очередь
        Student_res = Array_tests
        for item in Student_res:
            res_text = res_text + item.testName + ":"
            correct_num = 0
            ans_num = 0
            for question in item.questions:
                if question.is_answered != 0:
                    for answer in question.answers:
                        ans_num = ans_num + 1
                        if (answer.text == answer.student_text):
                            correct_num = correct_num + 1
                    percent_correct = correct_num / ans_num
                    res_text = res_text + str(percent_correct*100) + "%\n"
                else:
                    res_text = res_text + "Не пройден\n"
                    break
            
        await (message.answer
            (
            text = res_text,
        ))
        await(message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup=make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseAction)

    elif (message.text.lower() == '3'):
        await state.clear()
        await(message.answer
            (
            text="Выбор действия:\n1. Просмотр списка групп\n"
                 "2. Просмотр результатов тестов\n3. Выбор дисциплины",
            reply_markup=make_row_keyboard(['1', '2', '3'])
        ))
        await state.set_state(DecisionTree.ChooseAction)

    else:
        await (message.answer
            (
            text = "Выберите верную команду",
        ))

@router.message(DecisionTree.FirstQuestion)
async def answer_chosen(message: types.Message, state: FSMContext):
    user_data = await state.get_data()
    current_tests = user_data['CurrentTests']
    if (len(current_tests) < int(message.text.lower())):
        await (message.answer
            (
            text="Выберите верную команду",
        ))
    else:
        #chosen_test = GetTestById(id)        

        chosen_test =  current_tests[int(message.text.lower())-1]
        Current_questions = []
        for item in Array_ques:
            for question in chosen_test.questions:
                if (question == item):
                    item.is_answered = 0
                    Current_questions.append(item)

        buttons = Current_questions[0].buttons_text
        await (message.answer
            (
            text= "Вопрос " + str(1) + " из " + str(len(Current_questions)) + ":\n" + Current_questions[0].text,
            reply_markup=make_row_keyboard_arrows(buttons)
        ))
        await state.update_data(TestQuestions=Current_questions)
        await state.update_data(Length=1)
        await state.set_state(DecisionTree.Questions)

@router.message(DecisionTree.Questions)
async def answer_chosen(message: types.Message, state: FSMContext):
    user_data = await state.get_data()
    Current_questions = user_data['TestQuestions']
    if (message.text.lower() == "<-"):
        if (user_data['Length'] >= 1):
            length = user_data['Length'] - 2
        else:
            length = len(Current_questions) - 1
    elif (message.text.lower() == "->"):
        length = user_data['Length']
    else:
        length = user_data['Length']
        Current_questions[length - 1].is_answered = 1
        
    
    if (length >= len(Current_questions)):
        complete = 1
        for index in range(len(Current_questions)):
            if (Current_questions[index].is_answered == 0 and complete == 1):
                length = index
                complete = 0
        if complete == 0:
            buttons = Current_questions[length].buttons_text
            await(message.answer
            (
                text="Тест не завершён.\n" + "Вопрос " + str(length+1) + " из "
                    + str(len(Current_questions)) + ":\n" + Current_questions[length].text,
                reply_markup=make_row_keyboard_arrows(buttons)
            ))
            await state.update_data(Length=length+1)
            await state.set_state(DecisionTree.Questions)
        else:
            await state.clear()
            await(message.answer
                (
                text="Тест завершён."
                     "Выбор действия:\n1. Просмотр списка групп\n"
                     "2. Просмотр результатов тестов\n3. Выбор дисциплины",
                reply_markup=make_row_keyboard(['1', '2', '3'])
            ))
            await state.set_state(DecisionTree.ChooseAction)

    else:
        for item in Array_ans:
            for answer in Current_questions[length - 1].answers:
                if (answer == item):
                    current_answer = item
        if (message.text.lower() != "<-" and message.text.lower() != "->"):
            current_answer.student_text = message.text.lower()

        buttons = Current_questions[length].buttons_text
        await (message.answer
            (
            text= "Вопрос " + str(length+1) + " из "
                    + str(len(Current_questions)) + ":\n" + Current_questions[length].text,
            reply_markup=make_row_keyboard_arrows(buttons)
        ))
        await state.update_data(Length=length+1)
        await state.set_state(DecisionTree.Questions)

@router.message(DecisionTree.EndTest)
async def answer_chosen(message: types.Message, state: FSMContext):
    await state.clear()
    await(message.answer
        (
        text="Тест завершён."
             "Выбор действия:\n1. Просмотр списка групп\n"
             "2. Просмотр результатов тестов\n3. Выбор дисциплины",
        reply_markup=make_row_keyboard(['1', '2', '3'])
    ))
    await state.set_state(DecisionTree.ChooseAction)