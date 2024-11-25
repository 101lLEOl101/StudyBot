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
import handler

logging.basicConfig(level=logging.INFO)
async def main():
    response = requests.get("https://randomuser.me/api/")
    bot = Bot(token="8003753117:AAGCWbXANrymI_Qa3rRFM2zNLawPm-4BB7s")
    dp = Dispatcher()
    dp.include_routers(handler.router)
    await dp.start_polling(bot)

if __name__ == "__main__":
    asyncio.run(main())