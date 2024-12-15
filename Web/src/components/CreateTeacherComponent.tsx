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
import {useForm, UseFormReturnType} from '@mantine/form';
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
            password: (val) => (val.length <= 6 ? 'Пароль должен иметь не менее 6 символов' : null),
            repeat_password: (val, vals) => (val !== vals.password ? 'Повторный пароль неверен' : null)
        },
    });
    const CreateTeacherFun = async (form: UseFormReturnType<{ login: string; name: string; second_name: string; password: string; repeat_password: string }>) => {
        const body = {
            firstName: form.values.name,
            lastName: form.values.second_name,
            nickName: form.values.login,
            password: form.values.password,
            workerRole: 0
        };
        return (await axiosConfig.post('/api/worker/create', body)).data;
    }
    const [errorMessage, setErrorMessage] = useState("");
    const [loadingMessage, setLoadingMessage] = useState(false)

    const {mutate} = useMutation(CreateTeacherFun, {
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

    const handleCreate = () => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(form);
    };
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/teachers"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Преподаватель
            </Text>

            <Divider label={'Добавление'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Логин Преподавателя"
                        value={form.values.login}
                        onChange={(event) => form.setFieldValue('login', event.currentTarget.value)}
                        radius="md"
                    />

                    <TextInput
                        label="Имя"
                        placeholder="Имя Преподавателя"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
                    />

                    <TextInput
                        label="Фамилия"
                        placeholder="Фамилия Преподавателя"
                        value={form.values.second_name}
                        onChange={(event) => form.setFieldValue('second_name', event.currentTarget.value)}
                        radius="md"
                    />

                    <PasswordInput
                        label="Пароль"
                        placeholder="Пароль Преподавателя"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password && 'Пароль должен иметь не менее 6 символов'}
                        radius="md"
                    />

                    <PasswordInput
                        label="Повтор Пароля"
                        placeholder="Пароль Преподавателя"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password && 'Повторный пароль неверен'}
                        radius="md"
                    />
                </Stack>
                {errorMessage && (
                    <Text color="red" size="sm" mt="sm">
                        {errorMessage}
                    </Text>
                )}
                {loadingMessage && (
                    <Box pt = {10} style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                        <Loader size="lg"/>
                    </Box>
                )}
                <Group justify="end" mt="xl">
                    <Button onClick={handleCreate} type="submit" radius="xl" >
                        Добавить преподавателя
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}