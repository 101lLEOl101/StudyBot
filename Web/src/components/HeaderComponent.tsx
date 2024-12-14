import {
    Box,
    Button,
    Group,
    Image,
} from '@mantine/core';
import classes from '../styles/HeaderComponent.module.css';
import {Link} from "react-router-dom";

type HeaderComponentProps = {
    active_page: number;
}

export function HeaderComponent(props:HeaderComponentProps) {

    return (
        <Box pb={40}>
            <header className={classes.header}>
                <Group justify="space-between" h="100%">
                    <Image height={"80%"} radius="md"
                           src="https://auth.mephi.ru/assets/new_logo-0d4e8ce16244ce25269fe731be496247e0c9d6ea475a0f517150f4fb12a53579.png" />

                    <Group h="100%" gap={0} visibleFrom="sm">
                        <Link to={"/active-tests"}  className={props.active_page === 0 ? classes.activeLink + ' ' + classes.link : classes.link}>
                            Активные тесты
                        </Link>
                        <Link to={"/teachers"} className={props.active_page === 1 ? classes.activeLink + ' ' + classes.link : classes.link}>
                            Преподаватели
                        </Link>
                        <Link to={"/group-students"} className={props.active_page === 2 ? classes.activeLink + ' ' + classes.link : classes.link}>
                            Группы студентов
                        </Link>
                        <Link to={"/disciplines"} className={props.active_page === 3 ? classes.activeLink + ' ' + classes.link : classes.link}>
                            Дисциплины
                        </Link>
                        <Link to={"/tests"} className={props.active_page === 4 ? classes.activeLink + ' ' + classes.link : classes.link}>
                            Тестирования
                        </Link>
                    </Group>

                    <Group visibleFrom="sm">
                        <Link to={"/login"}><Button>Выйти</Button></Link>
                    </Group>
                </Group>
            </header>
        </Box>
    );
}