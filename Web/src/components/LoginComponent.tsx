import {
    Box,
    Button,
    Divider,
    Group,
    Loader,
    Paper,
    PaperProps,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch } from '../app/store.ts';
import { useEffect } from 'react';
import type { RootState } from '../app/store.ts';
import {userLogin} from "../features/auth/authThunk.ts";

export default function LoginComponent(props: PaperProps) {
    const form = useForm({
        initialValues: {
            nickName: '',
            password: '',
        },
    });

    const dispatch = useDispatch<AppDispatch>();
    const navigate = useNavigate();

    const { accessToken, loading, error } = useSelector(
        (state: RootState) => state.user
    );

    const handleLogin = async () => {
        const { nickName, password } = form.values;
        const resultAction = await dispatch(userLogin({ nickName, password }));
        if (userLogin.fulfilled.match(resultAction)) {
            navigate('/tests');
        }
    };

    useEffect(() => {
        if (accessToken) {
            navigate('/tests');
        }
    }, [accessToken]);

    return (
        <Paper radius="md" p="xl" withBorder {...props}>
            <Text size="lg" ta="center" fw={500}>
                Доступ к Study Bot
            </Text>

            <Divider label="Логин" labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(handleLogin)}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Твой Логин"
                        value={form.values.nickName}
                        onChange={(event) =>
                            form.setFieldValue('nickName', event.currentTarget.value)
                        }
                        radius="md"
                    />

                    <PasswordInput
                        label="Пароль"
                        placeholder="Твой Пароль"
                        value={form.values.password}
                        onChange={(event) =>
                            form.setFieldValue('password', event.currentTarget.value)
                        }
                        error={
                            form.errors.password && 'Пароль должен иметь не менее 6 символов'
                        }
                        radius="md"
                    />
                </Stack>

                {error && (
                    <Text color="red" size="sm" mt="sm">
                        {error}
                    </Text>
                )}

                {loading && (
                    <Box
                        pt={10}
                        style={{
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            height: '100%',
                        }}
                    >
                        <Loader size="lg" />
                    </Box>
                )}

                <Group justify="center" mt="xl">
                    <Button type="submit" radius="xl">
                        Войти
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}