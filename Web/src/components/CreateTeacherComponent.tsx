import {
    ActionIcon, Box,
    Button,
    Divider,
    Group, Loader,
    Paper,
    PaperProps,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import {useForm} from '@mantine/form';
import {Link, useNavigate} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";
import {useEffect, useState} from "react";
import {axiosConfig} from "../../axios.ts";
import {useMutation} from "@tanstack/react-query";

export function CreateTeacherComponent(props: PaperProps) {
    const navigate = useNavigate();

    useEffect(() => {
        const userId = localStorage.getItem('userRole');
        if (userId === "TEACHER") {
            navigate('/active-tests');
        }
    }, [navigate]);

    const form = useForm({
        initialValues: {
            login: '',
            name: '',
            second_name: '',
            password: '',
            repeat_password: '',
        },
        validate: {
            login: (val) => (val.trim().length === 0 ? 'Логин обязателен' : null),
            name: (val) => (val.trim().length === 0 ? 'Имя обязательно' : null),
            second_name: (val) => (val.trim().length === 0 ? 'Фамилия обязательна' : null),
            password: (val) => (val.length < 6 ? 'Пароль должен иметь не менее 6 символов' : null),
            repeat_password: (val, vals) =>
                val !== vals.password ? 'Повторный пароль неверен' : null,
        },
    });

    const CreateTeacherFun = async (formValues: typeof form.values) => {
        const body = {
            firstName: formValues.name,
            lastName: formValues.second_name,
            nickName: formValues.login,
            password: formValues.password,
            workerRole: 0,
        };
        return (await axiosConfig.post('/api/worker/create', body)).data;
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
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/teachers"}>
                    <ActionIcon radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32} />
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Преподаватель
            </Text>

            <Divider label={'Добавление'} labelPosition="center" my="lg" />

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

                    <PasswordInput
                        label="Пароль"
                        placeholder="Пароль Преподавателя"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password}
                        radius="md"
                    />

                    <PasswordInput
                        label="Повтор Пароля"
                        placeholder="Пароль Преподавателя"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password}
                        radius="md"
                    />
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
                        Добавить преподавателя
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}