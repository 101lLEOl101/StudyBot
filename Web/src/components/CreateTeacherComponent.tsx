import {
    ActionIcon, Box,
    Button,
    Divider,
    Group, Loader,
    Paper,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import {useForm} from '@mantine/form';
import {Link, useLocation, useNavigate} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";
import {useEffect, useState} from "react";
import {axiosConfig} from "../../axios.ts";
import {useMutation} from "@tanstack/react-query";
import {Worker} from "../Interfaces.ts";

export function CreateTeacherComponent() {
    const location = useLocation();
    const teacher = location.state?.teacher as Worker;
    const navigate = useNavigate();
    useEffect(() => {
        const userRole = JSON.parse(atob((localStorage.getItem("accessToken") || "").split('.')[1])).role;
        if (userRole === "TEACHER") {
            navigate('/active-tests');
        }
    }, [navigate]);
    const isEdit = !!teacher;

    const form = useForm({
        initialValues: {
            login: teacher?.nickName || '',
            name: teacher?.firstName || '',
            second_name: teacher?.lastName || '',
            password: '',
            repeat_password: '',
        },
        validate: {
            login: (val) => (val.trim().length === 0 ? 'Логин обязателен' : null),
            name: (val) => (val.trim().length === 0 ? 'Имя обязательно' : null),
            second_name: (val) => (val.trim().length === 0 ? 'Фамилия обязательна' : null),
            password: (val) => {
                if (!isEdit && val.trim().length < 6) return 'Пароль должен иметь не менее 6 символов';
                return null;
            },
            repeat_password: (val, vals) => {
                if (!isEdit && val !== vals.password) return 'Повторный пароль неверен';
                return null;
            },
        },
    });
    const CreateTeacherFun = async (formValues: typeof form.values) => {
        if(!teacher) {
            const body = {
                firstName: formValues.name,
                lastName: formValues.second_name,
                nickName: formValues.login,
                password: formValues.password,
                workerRole: 0,
            };
            return (await axiosConfig.post('/api/worker/create', body)).data;
        }
        else {
            const body = {
                workerId: teacher.id,
                firstName: formValues.name,
                lastName: formValues.second_name,
                nickName: formValues.login,
            };
            console.log(body);
            return (await axiosConfig.put('/api/worker/update', body)).data;
        }
    };

    const [errorMessage, setErrorMessage] = useState("");
    const [loadingMessage, setLoadingMessage] = useState(false);

    const { mutate } = useMutation(CreateTeacherFun, {
        onSuccess: () => {
            navigate('/teachers');
        },
        onError: () => {
            setErrorMessage("Ошибка создания");
        },
        onSettled: () => {
            setLoadingMessage(false);
        },
    });

    const handleSubmit = (values: typeof form.values) => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(values);
    };

    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/teachers"}>
                    <ActionIcon radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32} />
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                {teacher ? `Преподаватель ${teacher.firstName} ${teacher.lastName}` : 'Новый преподаватель'}
            </Text>
            {!teacher &&
                <Divider label={'Добавление'} labelPosition="center" my="lg" />
            }
            {teacher &&
                <Divider label={'Изменение'} labelPosition="center" my="lg" />
            }

            <form onSubmit={form.onSubmit(handleSubmit)}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Логин Преподавателя"
                        value={form.values.login}
                        onChange={(event) => form.setFieldValue('login', event.currentTarget.value)}
                        error={form.errors.login}
                        radius="md"
                    />

                    <TextInput
                        label="Имя"
                        placeholder="Имя Преподавателя"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        error={form.errors.name}
                        radius="md"
                    />

                    <TextInput
                        label="Фамилия"
                        placeholder="Фамилия Преподавателя"
                        value={form.values.second_name}
                        onChange={(event) => form.setFieldValue('second_name', event.currentTarget.value)}
                        error={form.errors.second_name}
                        radius="md"
                    />
                    { !teacher &&
                    <PasswordInput
                        label="Пароль"
                        placeholder="Пароль Преподавателя"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password}
                        radius="md"
                    />
                    }
                    { !teacher &&
                    <PasswordInput
                        label="Повтор Пароля"
                        placeholder="Пароль Преподавателя"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password}
                        radius="md"
                    />
                    }
                </Stack>
                {errorMessage && (
                    <Text color="red" size="sm" mt="sm">
                        {errorMessage}
                    </Text>
                )}
                {loadingMessage && (
                    <Box pt={10} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                        <Loader size="lg" />
                    </Box>
                )}
                <Group justify="end" mt="xl">
                    <Button type="submit" radius="xl">
                        {!teacher && "Добавить"}{teacher && "Изменить"} преподавателя
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}